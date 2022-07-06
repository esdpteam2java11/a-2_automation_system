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
public class UserForGroupDTO {
    private Long id;
    private String fio;
    private Date birthDate;
    private double amount;

    private String phone;


}
