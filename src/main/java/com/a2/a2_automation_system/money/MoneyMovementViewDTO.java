package com.a2.a2_automation_system.money;

import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentViewInMoneyMovementDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyMovementViewDTO {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private String counterpartyFIO;

    @NotNull
    private String cashierFIO;

    private String purpose;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private TypeOfFinance typeOfFinance;

    private Double amount;

    @NotNull
    private String moneyOperationType;

    private List<SportsmanPaymentViewInMoneyMovementDTO> sportsmanPaymentDTOList;

    public static MoneyMovementViewDTO from(MoneyMovement moneyMovement,
                                            List<SportsmanPayment> sportsmanPayments) {
        return builder()
                .id(moneyMovement.getId())
                .date(moneyMovement.getDate())
                .counterpartyFIO(moneyMovement.getCounterparty().getFIO())
                .cashierFIO(moneyMovement.getCashier().getFIO())
                .purpose(moneyMovement.getPurpose())
                .typeOfFinance(moneyMovement.getTypeOfFinance())
                .amount(moneyMovement.getAmount())
                .moneyOperationType(moneyMovement.getMoneyOperationType().getRusValue())
                .sportsmanPaymentDTOList(sportsmanPayments.stream().
                        map(SportsmanPaymentViewInMoneyMovementDTO::from).collect(Collectors.toList()))
                .build();
    }
}