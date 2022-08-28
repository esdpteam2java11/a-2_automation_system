package com.a2.a2_automation_system.sportsmanpayments;

import com.a2.a2_automation_system.group.GroupService;
import com.a2.a2_automation_system.user.Role;
import com.a2.a2_automation_system.user.UserDTO;
import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.YearMonth;

@Controller
@RequiredArgsConstructor
public class SportsmanPaymentController {
    private final SportsmanPaymentService sportsmanPaymentService;
    private final GroupService groupService;
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping("/all_debts")
    public String getAllDebtsReport(Model model,
                                    @RequestParam @Nullable YearMonth startDate,
                                    @RequestParam @Nullable YearMonth endDate,
                                    @RequestParam @Nullable Long groupId) {
        model.addAttribute("groups", groupService.getAllGroups());
        if (startDate != null && endDate != null && groupId != null) {
            String title = String.format("Отчет по группе \"%s\" за период с %s по %s. Цена группы: %s сом",
                    groupService.getGroupById(groupId).getName(), startDate, endDate,
                    groupService.getGroupById(groupId).getSum());
            model.addAttribute("title", title);
            model.addAttribute("sportsmenPayments", sportsmanPaymentService
                    .getSportsmanPayments(startDate, endDate, groupId));
        }
        return "all_debts_report";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping("/sportsman_debts")
    public String getSportsmanDebtsReport(Model model,
                                          @RequestParam @Nullable YearMonth startDate,
                                          @RequestParam @Nullable YearMonth endDate,
                                          @RequestParam @Nullable Long userId) {
        model.addAttribute("users", userService.getAllUsersByRole(Role.CLIENT));
        if (startDate != null && endDate != null && userId != null) {
            String title = String.format("Отчет по спортсмену \"%s\" за период с %s по %s.",
                    userService.getUserFio(userId), startDate, endDate);
            model.addAttribute("title", title);
            model.addAttribute("sportsmenPayments", sportsmanPaymentService
                    .getMonthlySportsmanPayments(startDate, endDate, userId));
        }

        return "sportsman_debts_report";
    }

    @PreAuthorize("hasAnyAuthority('CLIENT')")
    @GetMapping("/sportsman_debts_for_cabinet")
    public String getSportsmanDebtsReport(Model model,
                                          @RequestParam @Nullable YearMonth startDate,
                                          @RequestParam @Nullable YearMonth endDate,
                                          HttpServletRequest request) {
        UserDTO userDTO = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
        model.addAttribute("sportsman", userDTO);
        model.addAttribute("sportsmanFio", userService.getUserFio(userDTO.getId()));
        model.addAttribute("group", groupService.getGroupById(userDTO.getGroupId()).getName());

        if (startDate != null && endDate != null) {
            String title = String.format("Отчет по спортсмену \"%s\" за период с %s по %s.",
                    userService.getUserFio(userDTO.getId()), startDate, endDate);
            model.addAttribute("title", title);
            model.addAttribute("sportsmenPayments", sportsmanPaymentService
                    .getMonthlySportsmanPayments(startDate, endDate, userDTO.getId()));
        }

        return "sportsman_debts_report_for_cabinet";
    }
}
