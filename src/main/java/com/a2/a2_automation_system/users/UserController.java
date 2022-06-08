package com.a2.a2_automation_system.users;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping
    public String indexPage(Authentication principal) {
        try {
            String role = principal.getAuthorities().stream().map(a -> a.getAuthority()).findFirst().get();
            if (role.equals("Администратор")) {
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
    public String getAdmin(Model model
            , @RequestParam() String role,
              @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
               @RequestParam() boolean isActive){
        var sort = userService.listUser(pageable,role,isActive);
        model.addAttribute("users",sort.getContent());
        return "admin";
    }
}
