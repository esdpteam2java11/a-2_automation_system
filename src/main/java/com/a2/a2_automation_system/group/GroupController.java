package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.tariff.SportsmanPaymentRepository;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    private final UserRepository userRepository;
    private final SportsmanPaymentRepository sportsmanPayment;

    @GetMapping("/all")
    public String getAllGroups(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        return "groups";
    }

    @GetMapping("{id}")
    public String getGroupById(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("users", groupService.getUsersByGroup(id));
        return "group";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("trainers", groupService.getTrainers());
        return "add_group";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping("{id}/edit")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("trainers", groupService.getTrainers());
        model.addAttribute("group", groupService.getGroupById(id));
        return "edit_group";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @PostMapping("/edit")
    public String editGroups(@RequestParam Long id, @RequestParam String name,
                             @RequestParam(required = false) int sum, @RequestParam String color,
                             @RequestParam User trainer) {

        groupService.editGroup(id, trainer, name, sum, color);

        return "redirect:/group/all";
    }
}
