package com.a2.a2_automation_system.money;

import com.a2.a2_automation_system.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "moneys")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private TypeOfFinance typeOfFinance;
    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private User counterparty;
    @NotNull
    private Double amount;
    private String purpose;
    @NotNull
    private LocalDate date;
}
