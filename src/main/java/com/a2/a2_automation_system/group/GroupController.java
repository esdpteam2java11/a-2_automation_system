package com.a2.a2_automation_system.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("trainers", groupService.getTrainers());
        return "add_group";
    }

    @PostMapping("/add")
    public String addNewGroup(@Valid GroupDTO groupDTO,
                              BindingResult validationResult,
                              RedirectAttributes attributes) {
        if (validationResult.hasFieldErrors()) {
            attributes.addAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/group/add";
        }
        groupService.addGroup(groupDTO);
        return "redirect:/group/all";
    }

    @GetMapping("{id}/calendar")
    public String getCalendar(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "calendar";
    }
}
