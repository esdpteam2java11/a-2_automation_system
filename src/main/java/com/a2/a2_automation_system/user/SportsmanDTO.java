package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.userparam.UserParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanDTO {

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

    private Long groupId;

    private Date dateOfAdmission;

    private Double weight;

    private Double height;

    public static SportsmanDTO from(User user, UserParam userParam) {
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
                .groupId(user.getGroup() != null ? user.getGroup().getId() : null)
                .dateOfAdmission(user.getDateOfAdmission())
                .weight(userParam.getWeight())
                .height(userParam.getHeight())
                .build();
    }
}

