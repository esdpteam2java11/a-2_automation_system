package com.a2.a2_automation_system.money;

import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cash")
public class MoneyMovementController {
    private final UserService userService;
    private final MoneyMovementsService moneyMovementsService;

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping
    public String getMoneyMovements(Model model,
                                    @RequestParam (required = false) Long userId,
                                    @RequestParam (required = false) String typeOfFinance,
                                    @RequestParam (required = false) String operationType,
                                    @RequestParam(value = "periodStart", required = false) @DateTimeFormat(iso =
                                            DateTimeFormat.ISO.DATE) LocalDate periodStart,
                                    @RequestParam(value = "periodEnd", required = false) @DateTimeFormat(iso =
                                            DateTimeFormat.ISO.DATE) LocalDate periodEnd) {
        model.addAttribute("typesOfFinance", TypeOfFinance.values());
        model.addAttribute("operationTypes", OperationType.values());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("moneyMovements", moneyMovementsService.getMoneysByFilters(userId, typeOfFinance,
                operationType, periodStart, periodEnd));
        return "cash_flow";
    }
}

