package com.a2.a2_automation_system.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShortInfoDTO {
    private Long id;
    private String fio;
    private Date birthDate;
    private double amount;
    private String phone;
    private String backgroundColor;

    public static UserShortInfoDTO from(User user) {
        return builder()
                .id(user.getId())
                .fio(user.getSurname() + " " + user.getName() + (user.getPatronymic() != null ?
                        (" " + user.getPatronymic()) : ""))
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .backgroundColor("")
                .build();
    }

    public static UserShortInfoDTO fromBackground(User user) {
        return builder()
                .id(user.getId())
                .fio(user.getSurname() + " " + user.getName() + (user.getPatronymic() != null ?
                        (" " + user.getPatronymic()) : ""))
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .backgroundColor("background-color:#FF0000")
                .build();
    }
}
