package com.a2.a2_automation_system.sportsmanpayments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.YearMonth;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanPaymentForMonthDTO implements Comparable<SportsmanPaymentForMonthDTO> {

    @NotNull
    private Long sportsmanId;

    @NotNull
    private YearMonth yearMonth;

    private Double groupPrice = 0.0;

    private Double amountAccrued = 0.0;

    private Double discount = 0.0;

    private Double amountPaid = 0.0;

    private Double startBalance = 0.0;

    private Double endBalance = 0.0;

    public static SportsmanPaymentForMonthDTO from(Long userId, YearMonth yearMonth, Double groupPrice,
                                                   Double amountAccrued,
                                                   Double amountPaid, Double startBalance) {
        return builder()
                .sportsmanId(userId)
                .yearMonth(yearMonth)
                .groupPrice(groupPrice)
                .amountAccrued(amountAccrued)
                .discount(amountAccrued - amountPaid)
                .amountPaid(amountPaid)
                .startBalance(startBalance)
                .endBalance(startBalance + amountAccrued - amountPaid)
                .build();
    }

    @Override
    public int compareTo(SportsmanPaymentForMonthDTO o) {
        return this.yearMonth.compareTo(o.yearMonth);
    }
}
