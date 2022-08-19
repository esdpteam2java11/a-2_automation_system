package com.a2.a2_automation_system.sportsmanpayments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanPaymentDTO {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    private Long user;

    @NotNull
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private OperationType operationType;

    private Long moneyMovement;

    public static SportsmanPaymentDTO from(SportsmanPayment sportsmanPayment) {
        return builder()
                .id(sportsmanPayment.getId())
                .amount(sportsmanPayment.getAmount())
                .user(sportsmanPayment.getUser().getId())
                .date(sportsmanPayment.getDate())
                .operationType(sportsmanPayment.getOperationType())
                .moneyMovement(sportsmanPayment.getMoneyMovement() != null ?
                        sportsmanPayment.getMoneyMovement().getId() : null)
                .build();
    }
}
