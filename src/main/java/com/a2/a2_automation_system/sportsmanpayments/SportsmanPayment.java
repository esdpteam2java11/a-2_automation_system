package com.a2.a2_automation_system.sportsmanpayments;

import com.a2.a2_automation_system.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "sportsman_payments")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanPayment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "date")
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

}
