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

    @NotNull
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private User counterparty;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private User cashier;

    private String purpose;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private TypeOfFinance typeOfFinance;

    @NotNull
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ManyOperationType manyOperationType;
}
