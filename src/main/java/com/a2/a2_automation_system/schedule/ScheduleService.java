package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final GroupService groupService;

    public void addEventFromScheduleCreateDTO(ScheduleCreateDTO scheduleCreateDTO){
        Schedule schedule = Schedule.builder()
                .eventDate(scheduleCreateDTO.getEventStartDate())
                .startTime(scheduleCreateDTO.getTimeStart())
                .endTime(scheduleCreateDTO.getTimeEnd())
                .group(scheduleCreateDTO.getGroup())
                .build();
        scheduleRepository.save(schedule);
    }
    public void addEventFromScheduleDTO(ScheduleDTO scheduleDTO){
        Schedule schedule = Schedule.builder()
                .eventDate(scheduleDTO.getEventDate())
                .startTime(scheduleDTO.getStartTime())
                .endTime(scheduleDTO.getEndTime())
                .group(scheduleDTO.getGroup())
                .build();
        scheduleRepository.save(schedule);
    }

    public List<ScheduleRestDto> getEventsByGroupAndDates(String groupId, String dateStart,String dateEnd){
        LocalDate start = LocalDate.parse(dateStart.split("%")[0].split("T")[0]);
        LocalDate end = LocalDate.parse(dateEnd.split("%")[0].split("T")[0]);
        Group group = groupService.getGroupByIdReturnGroup(Long.parseLong(groupId));
        List<Schedule> events =  scheduleRepository.getAllByGroupAndEventDateBetween(group,start,end);
        return events.stream().map(ScheduleRestDto::from).collect(Collectors.toList());

    }

    public void addEventsFromScheduleCreateDto(ScheduleCreateDTO scheduleCreateDTO){
            if(scheduleCreateDTO.getRecurring()!=null){
                if(scheduleCreateDTO.getEventStartDate().isBefore(scheduleCreateDTO.getDateEnd())){
                   List<LocalDate> list = getDatesForDayOfWeek(scheduleCreateDTO);
                   for (LocalDate eventDate:list) {
                       var newScheduleDTO = ScheduleDTO.builder()
                               .eventDate(eventDate)
                               .startTime(scheduleCreateDTO.getTimeStart())
                               .endTime(scheduleCreateDTO.getTimeEnd())
                               .group(scheduleCreateDTO.getGroup())
                               .build();
                       addEventFromScheduleDTO(newScheduleDTO);
                   }
                }else {
                    throw new IllegalArgumentException("Введите правильную дату");
                }
            }
            else {
                addEventFromScheduleCreateDTO(scheduleCreateDTO);
            }
    }

    private List<LocalDate> getDatesForDayOfWeek(ScheduleCreateDTO scheduleCreateDTO){
        List<LocalDate> listOfDates = new ArrayList<>();
        LocalDate startDate = scheduleCreateDTO.getEventStartDate();
        LocalDate endDate = scheduleCreateDTO.getDateEnd();
        LocalDate thisDay = LocalDate.now();
        for(String day:scheduleCreateDTO.getDayOfWeek()){
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
                startDate = thisDayOfWeek.plusWeeks(1); // start on next monday
            } else {
                startDate = thisDayOfWeek; // start on this monday
            }
            while (startDate.isBefore(endDate)) {
                listOfDates.add(startDate);
                startDate = startDate.plusWeeks(1);
            }
            startDate = scheduleCreateDTO.getEventStartDate();

        }
        if(Arrays.stream(scheduleCreateDTO.getDayOfWeek()).anyMatch(endDate.getDayOfWeek().toString()::equals)){
            listOfDates.add(endDate);
        }
       return listOfDates;
    }
}
