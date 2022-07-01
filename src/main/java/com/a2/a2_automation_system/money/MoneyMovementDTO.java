package com.a2.a2_automation_system.money;

import com.a2.a2_automation_system.user.User;
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

    private Double amount;
    private String purpose;

    @NotNull
    @JsonProperty("counterparty_id")
    private User counterparty;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private TypeOfFinance typeOfFinance;

    public static MoneyMovementDTO from(MoneyMovement moneyMovement) {
        return builder()
                .id(moneyMovement.getId())
                .amount(moneyMovement.getAmount())
                .counterparty(moneyMovement.getCounterparty())
                .typeOfFinance(moneyMovement.getTypeOfFinance())
                .purpose(moneyMovement.getPurpose())
                .date(moneyMovement.getDate())
                .build();
    }

}
