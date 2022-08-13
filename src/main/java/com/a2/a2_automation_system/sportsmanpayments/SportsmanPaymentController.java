package com.a2.a2_automation_system.sportsmanpayments;

import com.a2.a2_automation_system.user.UserDTO;
import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class SportsmanPaymentController {
    private final SportsmanPaymentService sportsmanPaymentService;
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping("/all_debts")
    public String getAllDebtsReport(Model model, HttpServletRequest request){
        UserDTO user = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
        model.addAttribute("sportsman",user);
        return "all_debts_report";
    }
}
