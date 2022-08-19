package com.a2.a2_automation_system.sportsmanpayments;

import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanPaymentForPeriodDTO implements Comparable<SportsmanPaymentForPeriodDTO> {

    @NotNull
    private Long sportsmanId;

    @NotNull
    private String sportsmanFio;

    @NotNull
    private Long groupId;

    @NotNull
    private String groupName;

    private Double groupPrice = 0.0;

    private Double amountAccrued = 0.0;

    private Double discount = 0.0;

    private Double amountPaid = 0.0;

    private Double startBalance = 0.0;

    private Double endBalance = 0.0;

    public static SportsmanPaymentForPeriodDTO from(User user, Group group, Integer monthsQuantity,
                                                    Double amountAccrued, Double amountPaid, Double startBalance) {
        return builder()
                .sportsmanId(user.getId())
                .sportsmanFio(user.getSurname() + " " + user.getName() + (user.getPatronymic() != null ?
                        (" " + user.getPatronymic()) : ""))
                .groupId(group.getId())
                .groupName(group.getName())
                .groupPrice((double) group.getSum() * monthsQuantity)
                .amountAccrued(amountAccrued)
                .discount(amountAccrued - amountPaid)
                .amountPaid(amountPaid)
                .startBalance(startBalance)
                .endBalance(startBalance + amountAccrued - amountPaid)
                .build();
    }

    @Override
    public int compareTo(SportsmanPaymentForPeriodDTO o) {
        return this.sportsmanFio.compareTo(o.sportsmanFio);
    }
}
