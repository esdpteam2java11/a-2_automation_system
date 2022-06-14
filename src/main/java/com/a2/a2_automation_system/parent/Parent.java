package com.a2.a2_automation_system.parent;

import com.a2.a2_automation_system.commons.Role;
import com.a2.a2_automation_system.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "parent")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String patronymic;


    @NotBlank
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Kinship kinship;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

}
