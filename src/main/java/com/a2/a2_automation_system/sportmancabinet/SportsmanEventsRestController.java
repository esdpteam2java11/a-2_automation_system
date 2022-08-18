package com.a2.a2_automation_system.sportmancabinet;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SportsmanEventsRestController {
    private final SportsmanEventsService sportsmanEventsService;

    @GetMapping("/sportsman_cabinet/calendar/events")
    public List<SportsmanEventsRestDTO> getEventsForSportsman(@RequestParam String start, @RequestParam String end, HttpServletRequest request) {
        return sportsmanEventsService.getEventsBySportsmanAndDates(request.getRemoteUser(), start, end);
    }
}
