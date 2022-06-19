package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.parent.ParentDTO;
import com.a2.a2_automation_system.parent.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestUserController {
    private final ParentService parentService;

    @GetMapping("/create/parentSearch/{surnamePart}")
    public List<ParentDTO> getParents(@PathVariable @Nullable String surnamePart){
        return parentService.getParentsBySurname(surnamePart);
    }


}
