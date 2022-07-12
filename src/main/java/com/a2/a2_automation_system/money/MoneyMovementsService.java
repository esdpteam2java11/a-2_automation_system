package com.a2.a2_automation_system.money;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoneyMovementsService {
    private final MoneyMovementRepository moneyMovementRepository;

    public List<MoneyMovementDTO> getMoneysByFilters(Long userId, String typeOfFinance, String operationType, LocalDate periodStart, LocalDate periodEnd) {
        List<MoneyMovement> moneyMovements = moneyMovementRepository.getMoneysByFilters(userId, typeOfFinance,
                operationType, periodStart, periodEnd);
        return moneyMovements.stream().map(MoneyMovementDTO::from).collect(Collectors.toList());
    }
}
