package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final GroupService groupService;

    public void addEvent(ScheduleDTO scheduleDTO){
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
            if(scheduleCreateDTO.getRecurring().equals("on")){
                if(scheduleCreateDTO.getEventStartDate().isBefore(scheduleCreateDTO.getDateEnd())){
                    //todo
                }
            }
            else {
                //todo
            }
    }
}
