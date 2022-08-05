package com.a2.a2_automation_system.money;

import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cash")
public class MoneyMovementController {
    private final UserService userService;
    private final MoneyMovementsService moneyMovementsService;

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping
    public String getMoneyMovements(Model model,
                                    @RequestParam(required = false) Long userId,
                                    @RequestParam(required = false) String typeOfFinance,
                                    @RequestParam(required = false) String operationType,
                                    @RequestParam(value = "periodStart", required = false) @DateTimeFormat(iso =
                                            DateTimeFormat.ISO.DATE) LocalDate periodStart,
                                    @RequestParam(value = "periodEnd", required = false) @DateTimeFormat(iso =
                                            DateTimeFormat.ISO.DATE) LocalDate periodEnd) {
        model.addAttribute("typesOfFinance", TypeOfFinance.values());
        model.addAttribute("operationTypes", MoneyOperationType.values());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("moneyMovements", moneyMovementsService.getMoneysByFilters(userId, typeOfFinance,
                operationType, periodStart, periodEnd));
        return "cash_flow";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @GetMapping("/{typeOfFinance}")
    public String createIncome(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("operationTypes", MoneyOperationType.values());
        return "add_income";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @PostMapping("/{typeOfFinance}")
    public String registerUser(Principal principal, @PathVariable String typeOfFinance,
                               @RequestParam("moneyOperationType") @Nullable String moneyOperationType,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam("amount") Double amount,
                               @RequestParam("counterparty") @Nullable Long counterparty,
                               @RequestParam("purpose") @Nullable String purpose,

                               @RequestParam("dateSportsman") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                               @Nullable List<Date> datesSportsman,
                               @RequestParam("amountSportsman") @Nullable List<Double> amountSportsman) {

        moneyMovementsService.addMoneyMovement(principal.getName(), typeOfFinance, moneyOperationType,
                date, amount, counterparty, purpose, datesSportsman, amountSportsman);
        return "redirect:/cash";
    }
}

