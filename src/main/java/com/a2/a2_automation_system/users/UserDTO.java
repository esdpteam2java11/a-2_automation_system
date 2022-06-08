package com.a2.a2_automation_system.users;

import com.a2.a2_automation_system.commons.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotBlank
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Role role;

    private String login;

    @NotNull
    @JsonProperty("is_active")
    private Boolean isActive;
}
