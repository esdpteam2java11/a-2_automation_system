package com.a2.a2_automation_system.money;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyMovementDTO {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("event_date")
    private LocalDate date;

    @NotNull
    private String counterpartyFIO;

    private String purpose;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private TypeOfFinance typeOfFinance;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private MoneyOperationType moneyOperationType;

    public static MoneyMovementDTO from(MoneyMovement moneyMovement) {
        return builder()
                .id(moneyMovement.getId())
                .date(moneyMovement.getDate())
                .counterpartyFIO(moneyMovement.getCounterparty().getFIO())
                .purpose(moneyMovement.getPurpose())
                .typeOfFinance(moneyMovement.getTypeOfFinance())
                .amount(moneyMovement.getAmount())
                .moneyOperationType(moneyMovement.getMoneyOperationType())
                .build();
    }
}