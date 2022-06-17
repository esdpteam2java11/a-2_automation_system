package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.commons.Role;
import com.a2.a2_automation_system.users.User;
import com.a2.a2_automation_system.users.UserDTO;
import com.a2.a2_automation_system.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    private final UserRepository repository;

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
        return "redirect:/group/add";
    }
}
