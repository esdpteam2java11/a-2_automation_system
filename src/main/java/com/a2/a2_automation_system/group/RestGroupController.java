package com.a2.a2_automation_system.group;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class RestGroupController {
    private final GroupService groupService;

    @GetMapping("/group/color")
    public Boolean isColorExist(@RequestParam String color) {
        return groupService.isColorExist(color);
    }

    @GetMapping("/group/price/{id}")
    public Integer getGroupPrice(@PathVariable Long id) {
        return groupService.getGroupById(id).getSum();
    }
}
