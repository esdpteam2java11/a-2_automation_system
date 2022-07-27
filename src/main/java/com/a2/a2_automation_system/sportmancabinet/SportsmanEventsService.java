package com.a2.a2_automation_system.sportmancabinet;

import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.schedule.Schedule;
import com.a2.a2_automation_system.schedule.ScheduleRestDto;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportsmanEventsService {
    private final SportsmanEventsRepository sportsmanEventsRepository;
    private final UserService userService;

    public List<SportsmanEventsRestDTO> getEventsBySportsmanAndDates(String username, String dateStart, String dateEnd){
        LocalDate start = LocalDate.parse(dateStart.split("%")[0].split("T")[0]);
        LocalDate end = LocalDate.parse(dateEnd.split("%")[0].split("T")[0]);
        User user = userService.getUserByUsername(username);
        List<SportsmanEvents> events =  sportsmanEventsRepository.getSportsmanEventsBySportsmanAndEventDateBetween(user,start,end);
        return events.stream().map(SportsmanEventsRestDTO::from).collect(Collectors.toList());

    }
}
