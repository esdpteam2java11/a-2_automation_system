package com.a2.a2_automation_system.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String patronymic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birth_date")
    private Date birthDate;

    @NotBlank
    private String phone;

    private String whatsapp;

    private String telegram;

    @NotBlank
    private String address;

    private String school;

    private String channels;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Role role;

    private String login;

    private String password;

    @JsonProperty("is_active")
    private Boolean isActive;

    private Date dateOfAdmission;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .whatsapp(user.getWhatsapp())
                .telegram(user.getTelegram())
                .address(user.getAddress())
                .school(user.getSchool())
                .role(user.getRole())
                .login(user.getLogin())
                .password(user.getPassword())
                .isActive(user.getIsActive())
                .dateOfAdmission(user.getDateOfAdmission())
                .build();
    }
}

