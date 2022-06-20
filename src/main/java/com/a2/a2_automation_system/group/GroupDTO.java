package com.a2.a2_automation_system.group;


import com.a2.a2_automation_system.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {


    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @JsonProperty("trainer_id")
    private User trainer;

    public static GroupDTO from(Group group) {
        return builder()
                .id(group.getId())
                .name(group.getName())
                .trainer(group.getTrainer())
                .build();
    }
}
