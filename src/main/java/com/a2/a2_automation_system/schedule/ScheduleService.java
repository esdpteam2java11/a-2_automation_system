package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.exception.GroupNotFoundException;
import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.group.GroupService;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserService;
import com.a2.a2_automation_system.visit.Visit;
import com.a2.a2_automation_system.visit.VisitDto;
import com.a2.a2_automation_system.visit.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final VisitRepository visitRepository;
    private final UserService userService;

    public void addEventFromScheduleCreateDTO(ScheduleCreateDTO scheduleCreateDTO) {
        Schedule schedule = Schedule.builder()
                .eventDate(scheduleCreateDTO.getEventStartDate())
                .startTime(scheduleCreateDTO.getTimeStart())
                .endTime(scheduleCreateDTO.getTimeEnd())
                .group(scheduleCreateDTO.getGroup())
                .build();
        scheduleRepository.save(schedule);
    }

    public void addEventFromScheduleDTO(ScheduleDTO scheduleDTO) {
        Schedule schedule = Schedule.builder()
                .eventDate(scheduleDTO.getEventDate())
                .startTime(scheduleDTO.getStartTime())
                .endTime(scheduleDTO.getEndTime())
                .group(scheduleDTO.getGroup())
                .uniqueIdForSerialEvent(scheduleDTO.getUniqueIdForSerial())
                .build();
        scheduleRepository.save(schedule);
    }

    public List<ScheduleRestDto> getEventsByGroupAndDates(String groupId, String dateStart, String dateEnd) {
        LocalDate start = LocalDate.parse(dateStart.split("%")[0].split("T")[0]);
        LocalDate end = LocalDate.parse(dateEnd.split("%")[0].split("T")[0]);
        var group = groupRepository.findById(Long.parseLong(groupId));
        List<Schedule> events = scheduleRepository.getAllByGroupAndEventDateBetween(group.get(), start, end);
        return events.stream().map(ScheduleRestDto::from).collect(Collectors.toList());

    }

    @Transactional
    public void addEventsFromScheduleCreateDto(ScheduleCreateDTO scheduleCreateDTO) {
        if (scheduleCreateDTO.getRecurring() != null) {
            String uniqueIdForSerial = UUID.randomUUID().toString();
            if (scheduleCreateDTO.getEventStartDate().isBefore(scheduleCreateDTO.getDateEnd())) {
                List<LocalDate> list = getDatesForDayOfWeek(scheduleCreateDTO);
                for (LocalDate eventDate : list) {
                    var newScheduleDTO = ScheduleDTO.builder()
                            .eventDate(eventDate)
                            .startTime(scheduleCreateDTO.getTimeStart())
                            .endTime(scheduleCreateDTO.getTimeEnd())
                            .group(scheduleCreateDTO.getGroup())
                            .uniqueIdForSerial(uniqueIdForSerial)
                            .build();
                    addEventFromScheduleDTO(newScheduleDTO);
                }
            } else {
                throw new IllegalArgumentException("Введите правильную дату");
            }
        } else {
            addEventFromScheduleCreateDTO(scheduleCreateDTO);
        }
    }

    private List<LocalDate> getDatesForDayOfWeek(ScheduleCreateDTO scheduleCreateDTO) {
        List<LocalDate> listOfDates = new ArrayList<>();
        LocalDate startDate = scheduleCreateDTO.getEventStartDate();
        LocalDate endDate = scheduleCreateDTO.getDateEnd();
        LocalDate thisDay = LocalDate.now();
        for (String day : scheduleCreateDTO.getDayOfWeek()) {
            switch (day) {
                case "MONDAY":
                    thisDay = startDate.with(DayOfWeek.MONDAY);
                    break;
                case "TUESDAY":
                    thisDay = startDate.with(DayOfWeek.TUESDAY);
                    break;
                case "WEDNESDAY":
                    thisDay = startDate.with(DayOfWeek.WEDNESDAY);
                    break;
                case "THURSDAY":
                    thisDay = startDate.with(DayOfWeek.THURSDAY);
                    break;
                case "FRIDAY":
                    thisDay = startDate.with(DayOfWeek.FRIDAY);
                    break;
                case "SATURDAY":
                    thisDay = startDate.with(DayOfWeek.SATURDAY);
                    break;
                case "SUNDAY":
                    thisDay = startDate.with(DayOfWeek.SUNDAY);
                    break;
            }

            LocalDate thisDayOfWeek = startDate.with(thisDay);
            if (startDate.isAfter(thisDayOfWeek)) {
                startDate = thisDayOfWeek.plusWeeks(1);
            } else {
                startDate = thisDayOfWeek;
            }
            while (startDate.isBefore(endDate)) {
                listOfDates.add(startDate);
                startDate = startDate.plusWeeks(1);
            }
            startDate = scheduleCreateDTO.getEventStartDate();

        }
        if (Arrays.stream(scheduleCreateDTO.getDayOfWeek()).anyMatch(endDate.getDayOfWeek().toString()::equals)) {
            listOfDates.add(endDate);
        }
        return listOfDates;
    }

    public ScheduleDTO getEventById(Long eventId) {
        return ScheduleDTO.from(scheduleRepository.getById(eventId));
    }

    public List<VisitDto> getUsersWhoCame(Long eventId) {
        return visitRepository.findAllByScheduleId(eventId).stream().map(VisitDto::from).collect(Collectors.toList());
    }

    @Transactional
    public ScheduleDTO deleteEventById(Long eventId) {
        Schedule event = scheduleRepository.getById(eventId);
        ScheduleDTO scheduleDTO = ScheduleDTO.from(event);
        scheduleRepository.delete(event);
        return scheduleDTO;
    }

    @Transactional
    public ScheduleDTO deleteEventsInSeries(Long eventId) {
        Schedule event = scheduleRepository.getById(eventId);
        ScheduleDTO scheduleDTO = ScheduleDTO.from(event);
        var scheduleList = scheduleRepository.getAllByUniqueIdForSerialEventAndEventDateIsGreaterThanEqual(event.getUniqueIdForSerialEvent(), event.getEventDate());
        if (scheduleList.isPresent()) {
            for (Schedule ev : scheduleList.get()) {
                scheduleRepository.delete(ev);
            }
        }
        return scheduleDTO;
    }

    public void editEvent(ScheduleCreateDTO scheduleCreateDTO) {
        Schedule event = scheduleRepository.getById(scheduleCreateDTO.getId());
        event.setEventDate(scheduleCreateDTO.getEventStartDate());
        event.setStartTime(scheduleCreateDTO.getTimeStart());
        event.setEndTime(scheduleCreateDTO.getTimeEnd());
        scheduleRepository.save(event);
    }

    public List<ScheduleRestDto> getEventsForAll(String dateStart, String dateEnd) {
        LocalDate start = getLocalDateFromString(dateStart);
        LocalDate end = getLocalDateFromString(dateEnd);
        List<Schedule> events = scheduleRepository.getSchedulesByEventDateBetween(start, end);
        return events.stream().map(ScheduleRestDto::from).collect(Collectors.toList());
    }

    private LocalDate getLocalDateFromString(String date) {
        return LocalDate.parse(date.split("%")[0].split("T")[0]);
    }

    public String addEventTrainingProgram(String content, Long eventId) {
        String message = "Добавлено";
        var event = scheduleRepository.getById(eventId);
        if (event.getTrainingProgram() != null) {
            message = "Изменено";
        }
        event.setTrainingProgram(content);
        scheduleRepository.save(event);
        return message;
    }

    public List<ScheduleRestDtoForSportsman> getEventsForAllSportsman(String dateStart, String dateEnd) {
        LocalDate start = getLocalDateFromString(dateStart);
        LocalDate end = getLocalDateFromString(dateEnd);
        List<Schedule> events = scheduleRepository.getSchedulesByEventDateBetween(start, end);
        return events.stream().map(ScheduleRestDtoForSportsman::from).collect(Collectors.toList());
    }

    public List<ScheduleRestDtoForSportsman> getEventsByGroupAndDatesForSportsman(String groupId, String dateStart, String dateEnd) {
        LocalDate start = getLocalDateFromString(dateStart);
        LocalDate end = getLocalDateFromString(dateEnd);
        var group = groupRepository.findById(Long.parseLong(groupId));
        List<Schedule> events = scheduleRepository.getAllByGroupAndEventDateBetween(group.get(), start, end);
        return events.stream().map(ScheduleRestDtoForSportsman::from).collect(Collectors.toList());
    }

    public List<ScheduleRestDtoForSportsman> getEventsByGroupAndDatesForSportsmanAndMarkAttendance(String groupId, String dateStart, String dateEnd, String username) {
        User user = userService.getUserByUsername(username);
        LocalDate start = getLocalDateFromString(dateStart);
        LocalDate end = getLocalDateFromString(dateEnd);
        var group = groupRepository.findById(Long.parseLong(groupId));
        List<Schedule> events = scheduleRepository.getAllByGroupAndEventDateBetween(group.get(), start, end);
        List<Visit> visits = visitRepository.getAllByStudent(user);
        List<ScheduleRestDtoForSportsman> eventsDTO = events.stream().map(ScheduleRestDtoForSportsman::from).collect(Collectors.toList());
        List<ScheduleRestDtoForSportsman> modifiedEventsDto = new ArrayList<>();
        if (visits.size() > 0) {
            for (ScheduleRestDtoForSportsman event : eventsDTO) {
                for (Visit visit : visits) {
                    if (visit.getSchedule().getId() == event.getId()) {
                        String title = String.format("%s %s", event.getStart().toString().split("T")[1], "Присутствовал");
                        event.setTitle(title);
                    }
                }
                modifiedEventsDto.add(event);
            }
            return modifiedEventsDto;
        }
        return eventsDTO;
    }

    public List<Schedule> getListOfLastTreeEvents(Group group, LocalDate dateNow, LocalDate dateOfAdmission) {
        Comparator<Schedule> sortDesc = (schedule1, schedule2) -> schedule2.getEventDate().compareTo(schedule1.getEventDate());
        List<Schedule> listSchedule = scheduleRepository.getAllByGroupAndEventDateBetween(group, dateOfAdmission, dateNow);
        Collections.sort(listSchedule, sortDesc);
        return listSchedule.stream().limit(3).collect(Collectors.toList());
    }
}
