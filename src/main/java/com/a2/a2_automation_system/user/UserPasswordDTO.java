package com.a2.a2_automation_system.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO {

    @Size(min = 6,message = "минимальная длина 6 символов")
    @NotBlank
    private String newPassword;

    @Size(min = 6,message = "минимальная длина 6 символов")
    @NotBlank
    private String confirmPassword;
}
