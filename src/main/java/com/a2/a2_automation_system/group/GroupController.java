package com.a2.a2_automation_system.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/all")
    public String getAllGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "groups";
    }


    @GetMapping("{id}")
    public String getGroupById(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "group";
    }

}
