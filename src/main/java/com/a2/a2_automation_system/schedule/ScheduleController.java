package com.a2.a2_automation_system.schedule;


import com.a2.a2_automation_system.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
    private final GroupService groupService;

    @GetMapping("/group/{id}/calendar/events/create")
    public String getSchedule(@PathVariable Long id, Model model){
        model.addAttribute("group", groupService.getGroupById(id));
        return "add_schedule";
    }

    @PostMapping("/group/{id}/calendar/events/add")
    public String createEvent(@PathVariable String id,Model model,ScheduleCreateDTO scheduleCreateDTO){
        String pathRedirect = String.format("redirect:/group/%s/calendar",id);
        System.out.println(scheduleCreateDTO);
        return pathRedirect;
    }
}
