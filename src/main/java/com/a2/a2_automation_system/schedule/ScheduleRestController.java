package com.a2.a2_automation_system.schedule;


import com.a2.a2_automation_system.parent.ParentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleRestController {
  private final ScheduleService scheduleService;

    @GetMapping("/group/{id}/calendar/events")
    public List<ScheduleRestDto> getSchedule(@RequestParam String start,@RequestParam String end,@PathVariable String id){
        List<ScheduleRestDto> scheduleRestDtoList = new ArrayList<>();
        if(id.equals("all")){
            scheduleRestDtoList = scheduleService.getEventsForAll(start,end);
        }
        else {
            scheduleRestDtoList = scheduleService.getEventsByGroupAndDates(id, start, end);
        }

        return scheduleRestDtoList;
    }

    }
