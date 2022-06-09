package com.a2.a2_automation_system.users;

import com.a2.a2_automation_system.commons.Role;
import com.a2.a2_automation_system.config.PropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PropertiesService propertiesService;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }
        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }
        List<String> roles = Stream.of(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("users", list.getContent());
        model.addAttribute("roles",roles);
        model.addAttribute("defaultPageSize");
    }
    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

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
    public String getAdmin(Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC,size = 1) Pageable pageable,
                            HttpServletRequest uriBuilder) {
        var userDTOS = userService.getAllUsers(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(userDTOS,propertiesService.getDefaultPageSize(),model,uri);
      return "admin";
    }

    @GetMapping("/filter/{path}")
    public String filterAdmin(@PathVariable String path, Model model, Pageable pageable, HttpServletRequest uriBuilder){
        System.out.println(path);
        var role = Role.valueOf(path.split("/")[0]);
        var isActive = Boolean.valueOf(path.split("/")[1]) ;
        var sort = userService.listUser(pageable, role, isActive);
        var uri = uriBuilder.getRequestURI();
        constructPageable(sort,propertiesService.getDefaultPageSize(),model,uri);
        return "admin";
    }

}
