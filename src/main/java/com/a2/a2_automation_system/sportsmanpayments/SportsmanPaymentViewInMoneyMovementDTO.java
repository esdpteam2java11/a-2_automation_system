package com.a2.a2_automation_system.sportsmanpayments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.YearMonth;
import java.time.ZoneId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanPaymentViewInMoneyMovementDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private YearMonth date;

    @NotNull
    private Double amount;

    private Long moneyMovement;

    public static SportsmanPaymentViewInMoneyMovementDTO from(SportsmanPayment sportsmanPayment) {
        YearMonth yearMonth = YearMonth.from(sportsmanPayment.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        return builder()
                .amount(sportsmanPayment.getAmount() < 0 ? ((-1) * sportsmanPayment.getAmount())
                        : sportsmanPayment.getAmount())
                .date(yearMonth)
                .moneyMovement(sportsmanPayment.getMoneyMovement() != null ?
                        sportsmanPayment.getMoneyMovement().getId() : null)
                .build();
    }
}
