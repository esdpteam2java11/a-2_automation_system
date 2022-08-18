package com.a2.a2_automation_system.sportmancabinet;

import com.a2.a2_automation_system.exception.ResourceNotFoundException;
import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.schedule.Schedule;
import com.a2.a2_automation_system.schedule.ScheduleDTO;
import com.a2.a2_automation_system.schedule.ScheduleRepository;
import com.a2.a2_automation_system.schedule.ScheduleService;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserService;
import com.a2.a2_automation_system.visit.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportsmanEventsService {
    private final SportsmanEventsRepository sportsmanEventsRepository;
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleService scheduleService;
    private final VisitService visitService;

    public List<SportsmanEventsRestDTO> getEventsBySportsmanAndDates(String username, String dateStart, String dateEnd){
        LocalDate start = LocalDate.parse(dateStart.split("%")[0].split("T")[0]);
        LocalDate end = LocalDate.parse(dateEnd.split("%")[0].split("T")[0]);
        User user = userService.getUserByUsername(username);
        List<SportsmanEvents> events =  sportsmanEventsRepository.getSportsmanEventsBySportsmanAndEventDateBetween(user,start,end);
        return events.stream().map(SportsmanEventsRestDTO::from).collect(Collectors.toList());

    }

    @Transactional
    public void addEventFromSportsmanEventsDTO(SportsmanEventsDTO sportsmanEventsDTO){
        SportsmanEvents sportsmanEvents = SportsmanEvents.builder()
                .eventDate(sportsmanEventsDTO.getEventDate())
                .sportsman(sportsmanEventsDTO.getSportsman())
                .food(sportsmanEventsDTO.getFood())
                .title(sportsmanEventsDTO.getTitle())
                .trainingProgram(sportsmanEventsDTO.getTrainingProgram())
                .uniqueIdForSerialEvent(sportsmanEventsDTO.getUniqueIdForSerialEvent())
                .build();
        sportsmanEventsRepository.save(sportsmanEvents);
    }

    @Transactional
    public void createEventsFromScheduleCreateDto(SportsmanEventCreateDTO sportsmanEventCreateDTO,User user) {
        if(sportsmanEventCreateDTO.getRecurring()!=null){
            String uniqueIdForSerial = UUID.randomUUID().toString();
             List<LocalDate> list = getDatesForDayOfWeek(sportsmanEventCreateDTO);
                for (LocalDate eventDate:list) {
                    SportsmanEventsDTO sportsmanEventsDTO = SportsmanEventsDTO.builder()
                            .eventDate(eventDate)
                            .sportsman(user)
                            .title(sportsmanEventCreateDTO.getTitle())
                            .uniqueIdForSerialEvent(uniqueIdForSerial)
                            .build();
                    addEventFromSportsmanEventsDTO(sportsmanEventsDTO);
                }
        }
        else {
            SportsmanEventsDTO sportsmanEventsDTO = SportsmanEventsDTO.builder()
                    .eventDate(sportsmanEventCreateDTO.getEventDate())
                    .sportsman(user)
                    .title(sportsmanEventCreateDTO.getTitle())
                    .build();
            addEventFromSportsmanEventsDTO(sportsmanEventsDTO);
        }
    }

    private List<LocalDate> getDatesForDayOfWeek(SportsmanEventCreateDTO sportsmanEventCreateDTO){
        List<LocalDate> listOfDates = new ArrayList<>();
        LocalDate startDate = sportsmanEventCreateDTO.getEventDate();
        LocalDate endDate = sportsmanEventCreateDTO.getDateEnd();
        LocalDate thisDay = LocalDate.now();
        for(String day:sportsmanEventCreateDTO.getDayOfWeek()){
            switch (day){
                case "MONDAY": thisDay = startDate.with(DayOfWeek.MONDAY);
                    break;
                case "TUESDAY": thisDay = startDate.with(DayOfWeek.TUESDAY);
                    break;
                case "WEDNESDAY": thisDay = startDate.with(DayOfWeek.WEDNESDAY);
                    break;
                case "THURSDAY": thisDay = startDate.with(DayOfWeek.THURSDAY);
                    break;
                case "FRIDAY": thisDay = startDate.with(DayOfWeek.FRIDAY);
                    break;
                case "SATURDAY": thisDay = startDate.with(DayOfWeek.SATURDAY);
                    break;
                case "SUNDAY": thisDay = startDate.with(DayOfWeek.SUNDAY);
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
            startDate = sportsmanEventCreateDTO.getEventDate();

        }
        if(Arrays.stream(sportsmanEventCreateDTO.getDayOfWeek()).anyMatch(endDate.getDayOfWeek().toString()::equals)){
            listOfDates.add(endDate);
        }
        return listOfDates;
    }

    public SportsmanEventsDTO getEventById(Long id){
        return SportsmanEventsDTO.from(sportsmanEventsRepository.getSportsmanEventsById(id));

    }

    public void editEvent(SportsmanEventCreateDTO sportsmanEventCreateDTO,Long id) {
       var event = sportsmanEventsRepository .getSportsmanEventsById(id);
       event.setEventDate(sportsmanEventCreateDTO.getEventDate());
        sportsmanEventsRepository.save(event);
    }

    @Transactional
    public SportsmanEventsDTO deleteEventById(Long eventId) {
        SportsmanEvents event = sportsmanEventsRepository.getSportsmanEventsById(eventId);
        SportsmanEventsDTO sportsmanEventsDTO = SportsmanEventsDTO.from(event);
        sportsmanEventsRepository.delete(event);
        return sportsmanEventsDTO;
    }

    @Transactional
    public SportsmanEventsDTO deleteEventsInSeries(Long eventId) {
        SportsmanEvents event = sportsmanEventsRepository.getSportsmanEventsById(eventId);
        SportsmanEventsDTO sportsmanEventsDTO = SportsmanEventsDTO.from(event);
        var sportsmanEventsList = sportsmanEventsRepository.getAllByUniqueIdForSerialEventAndEventDateIsGreaterThanEqual(event.getUniqueIdForSerialEvent(),event.getEventDate());
        if(sportsmanEventsList.isPresent()){
            for(SportsmanEvents ev:sportsmanEventsList.get()){
                sportsmanEventsRepository.delete(ev);
            }
        }
        return sportsmanEventsDTO;
    }


    public String addEventTrainingProgram(String content,Long eventId) {
        String message = "Добавлено";
        var event = sportsmanEventsRepository.getSportsmanEventsById(eventId);
        if(event.getTrainingProgram()!=null){
            message = "Изменено";
        }
        event.setTrainingProgram(content);
        sportsmanEventsRepository.save(event);
        return message;
    }

    public String addEventFood(String contentFood,Long eventId) {
        String message = "Добавлено";
        var event = sportsmanEventsRepository.getSportsmanEventsById(eventId);
        if(event.getFood()!=null){
            message = "Изменено";
        }
        event.setFood(contentFood);
        sportsmanEventsRepository.save(event);
        return message;
    }

    public ScheduleDTO getEventByID(Long eventId) {
        return ScheduleDTO.from(scheduleRepository.findById(eventId) .orElseThrow(() -> new ResourceNotFoundException("Такой задачи с таким id нет")));
    }

}
