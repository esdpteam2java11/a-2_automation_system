package com.a2.a2_automation_system.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleRestController {
    private final ScheduleService scheduleService;

    @GetMapping("/group/{id}/calendar/events")
    public List<ScheduleRestDto> getSchedule(@RequestParam String start, @RequestParam String end, @PathVariable String id) {
        List<ScheduleRestDto> scheduleRestDtoList = new ArrayList<>();
        if (id.equals("all")) {
            scheduleRestDtoList = scheduleService.getEventsForAll(start, end);
        } else {
            scheduleRestDtoList = scheduleService.getEventsByGroupAndDates(id, start, end);
        }

        return scheduleRestDtoList;
    }

    @GetMapping("/calendar_sportsman/{id}/calendar/events")
    public List<ScheduleRestDtoForSportsman> getScheduleForSportsman(@RequestParam String start, @RequestParam String end, @PathVariable String id) {
        List<ScheduleRestDtoForSportsman> scheduleRestDtoForSportsman;
        if (id.equals("all")) {
            scheduleRestDtoForSportsman = scheduleService.getEventsForAllSportsman(start, end);
        } else {
            scheduleRestDtoForSportsman = scheduleService.getEventsByGroupAndDatesForSportsman(id, start, end);
        }

        return scheduleRestDtoForSportsman;
    }

    @GetMapping("/calendar_sportsman/{id}/attendance/calendar/events")
    public List<ScheduleRestDtoForSportsman> getScheduleAndAttendanceForSportsman(@RequestParam String start, @RequestParam String end, @PathVariable String id, HttpServletRequest request) {
        var scheduleRestDtoForSportsmanList = scheduleService.getEventsByGroupAndDatesForSportsmanAndMarkAttendance(id, start, end, request.getRemoteUser());
        return scheduleRestDtoForSportsmanList;
    }

}
