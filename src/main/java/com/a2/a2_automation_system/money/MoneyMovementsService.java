package com.a2.a2_automation_system.money;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyMovementsService {
    private final MoneyMovementRepository moneyMovementRepository;

    public List<MoneyMovementDTO> getMoneysByFilters(Long userId, String typeOfFinance, String operationType, LocalDate periodStart, LocalDate periodEnd) {
        List<MoneyMovement> moneyMovements = moneyMovementRepository.getMoneysByFilters(userId, typeOfFinance,
                operationType, periodStart, periodEnd);
        System.out.println(moneyMovements.toString());
        List<MoneyMovementDTO> moneyMovementsDTO = new ArrayList<>();
        return moneyMovementsDTO;
    }
}
