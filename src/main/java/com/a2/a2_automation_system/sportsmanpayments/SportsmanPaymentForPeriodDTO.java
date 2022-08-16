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
public class SportsmanPaymentForPeriodDTO {

    @NotNull
    private Long sportsmanId;

    @NotNull
    private String sportsmanFio;

    @NotNull
    private Long groupId;

    @NotNull
    private String groupName;

    @NotNull
    private Double groupPrice;

    private Double amountAccrued;

    private Double amountPaid;

    public static SportsmanPaymentForPeriodDTO from(User user, Group group, Integer monthsQuantity,
                                                    Double amountAccrued, Double amountPaid) {
        return builder()
                .sportsmanId(user.getId())
                .sportsmanFio(user.getSurname() + " " + user.getName() + (user.getPatronymic() != null ?
                        (" " + user.getPatronymic()) : ""))
                .groupId(group.getId())
                .groupName(group.getName())
                .groupPrice((double) group.getSum() * monthsQuantity)
                .amountAccrued(amountAccrued)
                .amountPaid(amountPaid)
                .build();
    }
}
