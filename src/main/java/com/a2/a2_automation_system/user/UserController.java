package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.config.PropertiesService;
import com.a2.a2_automation_system.group.GroupService;
import com.a2.a2_automation_system.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PropertiesService propertiesService;
    private final GroupService groupService;


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
    public String getAdmin(Model model, @RequestParam @Nullable String role,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                           HttpServletRequest uriBuilder,
                           @RequestParam @Nullable Boolean isActive,
                           @RequestParam(value = "search", defaultValue = "", required = false) String search) {
        Page<UserDTO> page = userService.getUsersWithFilter(pageable, role, isActive);
        String uri = uriBuilder.getRequestURI();
        PageUtil.constructPageable(page, propertiesService.getDefaultPageSize(), model, uri, role, isActive);
        if (!search.isEmpty()) {
            Page<UserDTO> searchUserByNameOrSurnameOrPatronymic = userService.getUserBySearch(pageable, search);
            PageUtil.constructPageable(searchUserByNameOrSurnameOrPatronymic, propertiesService.getDefaultPageSize(), model, uri + "?" + "search=" + search, role, isActive);
        }
        return "admin";
    }


    @GetMapping("/add/trainer")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new UserDTO());
        }
        return "add_trainer";
    }

    @PostMapping("/add/trainer")
    public String registerPage(@Valid UserDTO customerRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes) {
        if (userService.userLoginCheck(customerRequestDto.getLogin())) {
            attributes.addFlashAttribute("dto", customerRequestDto);
            return "redirect:/add/trainer";
        }
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/add/trainer";
        }
        userService.addTrainer(customerRequestDto);
        return "redirect:/add/trainer";
    }

    @GetMapping("/create")
    public String viewCreateUser(Model model, Authentication principal) {
        try {
            String role = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get();
            if (role.equals("ADMIN")) {
                model.addAttribute("groups", groupService.getAllGroups());
                return "add_sportsman";
            } else return "main";
        } catch (NullPointerException e) {
            return "index";
        }
    }

    @PostMapping("/create")
    public String registerUser(@RequestParam("surname") String surname, @RequestParam("name") String name,
                               @RequestParam("patronymic") @Nullable String patronymic,
                               @RequestParam("birthDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDate,
                               @RequestParam("growth") Double growth, @RequestParam("weight") Double weight,
                               @RequestParam("phone") String phone, @RequestParam("whatsapp") @Nullable String whatsapp,
                               @RequestParam("telegram") @Nullable String telegram, @RequestParam("address") String address,
                               @RequestParam("school") @Nullable String school, @RequestParam("channels") @Nullable String channels,
                               @RequestParam("group") Long groupId,
                               @RequestParam("dateOfAdmission") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfAdmission,
                               @RequestParam("login") @Nullable String login, @RequestParam("password") @Nullable String password,

                               @RequestParam("p_id") @Nullable List<Long> pIds,
                               @RequestParam("p_kinship") @Nullable List<String> pKinships,
                               @RequestParam("p_surname") @Nullable List<String> pSurnames,
                               @RequestParam("p_name") @Nullable List<String> pNames,
                               @RequestParam("p_patronymic") @Nullable List<String> pPatronymics,
                               @RequestParam("p_phone") @Nullable List<String> pPhones,
                               @RequestParam("p_whatsapp") @Nullable List<String> pWhatsapps,
                               @RequestParam("p_telegram") @Nullable List<String> pTelegrams) {

        userService.createSportsman(surname, name, patronymic, birthDate, growth, weight, phone, whatsapp, telegram,
                address, school, channels, groupId, dateOfAdmission, login, password, pIds, pKinships, pSurnames,
                pNames, pPatronymics, pPhones, pWhatsapps, pTelegrams);

        return "redirect:/admin";
    }

    @GetMapping("/editSportsman/{id}")
    public String viewUserDetails(Model model, @PathVariable Long id, Authentication principal) {
        try {
            String role = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get();
            if (role.equals("ADMIN")) {
                model.addAttribute("groups", groupService.getAllGroups());
                model.addAttribute("user", userService.getUserDetails(id));
                return "edit_sportsman";
            } else return "main";
        } catch (NullPointerException e) {
            return "index";
        }
    }
}
