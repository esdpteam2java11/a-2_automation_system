package com.a2.a2_automation_system.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO {

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmPassword;
}
