package com.a2.a2_automation_system.sportsmanpayments;

import com.a2.a2_automation_system.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.YearMonth;

@Controller
@RequiredArgsConstructor
public class SportsmanPaymentController {
    private final SportsmanPaymentService sportsmanPaymentService;
    private final GroupService groupService;

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
}
