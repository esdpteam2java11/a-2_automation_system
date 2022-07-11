package com.a2.a2_automation_system.money;

import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cash")
public class MoneyMovementController {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping
    public String getMoneyMovements(Model model) {
        model.addAttribute("typesOfFinance", TypeOfFinance.getRusValues());
        model.addAttribute("operationTypes", OperationType.getRusValues());
        model.addAttribute("users", userService.getAllUsers());
        return "cash_flow";
    }
}

