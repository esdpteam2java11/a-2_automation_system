package com.a2.a2_automation_system.money;

import com.a2.a2_automation_system.sportsmanpayments.OperationType;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentRepository;
import com.a2.a2_automation_system.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoneyMovementsService {
    private final MoneyMovementRepository moneyMovementRepository;
    private final SportsmanPaymentRepository sportsmanPaymentRepository;
    private final UserRepository userRepository;

    public List<MoneyMovementDTO> getMoneysByFilters(Long userId, String typeOfFinance, String operationType, LocalDate periodStart, LocalDate periodEnd) {
        List<MoneyMovement> moneyMovements = moneyMovementRepository.getMoneysByFilters(userId, typeOfFinance,
                operationType, periodStart, periodEnd);
        return moneyMovements.stream().map(MoneyMovementDTO::from).collect(Collectors.toList());
    }

    public void addMoneyMovement(String cashier, String typeOfFinance, String moneyOperationType, LocalDate date,
                                 Double amount, Long counterparty, String purpose,
                                 List<YearMonth> dateSportsman, List<Double> amountSportsman) {

        MoneyMovement movement = MoneyMovement.builder()
                .amount(amount)
                .date(date)
                .purpose(purpose)
                .counterparty(userRepository.findById(counterparty).orElseThrow())
                .moneyOperationType(MoneyOperationType.valueOf(moneyOperationType))
                .typeOfFinance(TypeOfFinance.valueOf(typeOfFinance))
                .cashier(userRepository.findByLogin(cashier).get())
                .build();
        moneyMovementRepository.save(movement);

        if (dateSportsman != null && dateSportsman.size() > 0 && amountSportsman.size() == dateSportsman.size()) {
            for (int i = 0; i < dateSportsman.size(); i++) {
                LocalDate localDate = dateSportsman.get(i).atDay(1);
                Date dateOfMonth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                sportsmanPaymentRepository.save(
                        SportsmanPayment.builder()
                                .amount((moneyOperationType.equals(MoneyOperationType.SPORTSMAN_PAYMENT.toString())) ?
                                        amountSportsman.get(i) : (amountSportsman.get(i)) * (-1))
                                .operationType(OperationType.PAID)
                                .user(userRepository.findById(counterparty).get())
                                .date(dateOfMonth)
                                .moneyMovement(movement)
                                .build());
            }
        }
    }

    public Boolean isIncome(Long id) {
        return moneyMovementRepository.existsByIdAndTypeOfFinance(id, TypeOfFinance.INCOME);
    }

    public MoneyMovementViewDTO viewMoneyMovement(Long id) {
        Optional<MoneyMovement> moneyMovement = moneyMovementRepository.findById(id);
        if (moneyMovement.isPresent()) {
            List<SportsmanPayment> sportsmanPayments = sportsmanPaymentRepository.findAllByMoneyMovementId(id);
            return MoneyMovementViewDTO.from(moneyMovement.get(), sportsmanPayments);
        }
        return null;
    }

    @Transactional
    public void deleteMoneyMovement(Long id) {
        Optional<MoneyMovement> moneyMovement = moneyMovementRepository.findById(id);
        if (moneyMovement.isPresent()) {
            sportsmanPaymentRepository.deleteAllByMoneyMovementId(id);
            moneyMovementRepository.deleteById(id);
        }
    }
}
