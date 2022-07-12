package com.a2.a2_automation_system.money;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyMovementsService {
    private final MoneyMovementRepository moneyMovementRepository;

    public List<MoneyMovementDTO> getMoneysByFilters(Long userId, String typeOfFinance, String operationType, Date periodStart, Date periodEnd) {
        List<MoneyMovement> moneyMovements = new ArrayList<>();
        List<MoneyMovementDTO> moneyMovementsDTO = new ArrayList<>();
        return moneyMovementsDTO;
    }
}
