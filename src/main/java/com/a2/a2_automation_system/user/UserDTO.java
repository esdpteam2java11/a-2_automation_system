package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.group.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Нужно вводить цифры")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAdmission;

    public static UserDTO from(User user) {
        return UserDTO.builder()
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
                .build();
    }
}

