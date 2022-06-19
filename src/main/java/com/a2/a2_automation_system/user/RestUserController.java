package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.config.PropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestUserController {
    private final UserService userService;
    private final PropertiesService propertiesService;

//    @GetMapping("/create/parentSearch")
//    public List<ParentDTO> getParentsByFilter(@RequestParam @Nullable String filter){
//        TO DO
//        return null;
//    }
}
