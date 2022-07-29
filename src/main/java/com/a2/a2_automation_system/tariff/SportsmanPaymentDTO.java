package com.a2.a2_automation_system.tariff;

import com.a2.a2_automation_system.user.SportsmanDTO;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.userparam.UserParam;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private OperationType operationType;


    public static SportsmanPaymentDTO from(SportsmanPayment sportsmanPayment) {
        return builder()
                .id(sportsmanPayment.getId())
                .amount(sportsmanPayment.getAmount())
                .user(sportsmanPayment.getUser())
                .date(sportsmanPayment.getDate())
                .operationType(sportsmanPayment.getOperationType())
                .build();
    }

}
