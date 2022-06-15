package com.a2.a2_automation_system.users;

import com.a2.a2_automation_system.config.PropertiesService;
import com.a2.a2_automation_system.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String indexPage(Authentication principal) {
        try {
            String role = principal.getAuthorities().stream().map(a -> a.getAuthority()).findFirst().get();

            if (role.equals("ADMIN")) {
                return "redirect:/admin";
            }
            if (role.isEmpty()) {
                return "index";
            }
            return "redirect:/main";
        } catch (NullPointerException e) {
            return "index";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/main")
    public String getIndex(Model model, Authentication principal) {
        try {
            String role = principal.getAuthorities().stream().map(a -> a.getAuthority()).findFirst().get();
            model.addAttribute("role", role);
            return "main";
        } catch (NullPointerException e) {
            return "login";
        }
    }

    @GetMapping("/admin")
    public String getAdmin(Model model, @RequestParam @Nullable String role,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                           HttpServletRequest uriBuilder, @RequestParam @Nullable Boolean isActive) {
        Page<UserDTO> page = userService.listUser(pageable, role, isActive);
        var uri = uriBuilder.getRequestURI();
        PageUtil.constructPageable(page, propertiesService.getDefaultPageSize(), model, uri, role, isActive);
        return "admin";
    }
}
