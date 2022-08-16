package com.a2.a2_automation_system.sportsmanpayments;

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

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping("/all_debts")
    public String getAllDebtsReport(Model model,
                                    @RequestParam @Nullable YearMonth startDate,
                                    @RequestParam @Nullable YearMonth endDate){
        if (startDate!=null && endDate!=null) {
            model.addAttribute("sportsmenPayments",sportsmanPaymentService
                    .getSportsmanPayments(startDate, endDate));
        }
        return "all_debts_report";
    }
}
