package com.a2.a2_automation_system.userparam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserParamDTO {

    private Long id;

    @NotNull
    @JsonProperty("creation_date")
    private Date creationDate;

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    @Size(min = 10, message = "Вес должен быть больше или равно 10")
    private Double weight;

    @NotNull
    @Size(min = 20, message = "Рост должен быть больше или равно 20")
    private Double height;

    public static UserParamDTO from(UserParam userParam) {
        return builder()
                .id(userParam.getId())
                .creationDate(userParam.getCreationDate())
                .userId(userParam.getUser().getId())
                .weight(userParam.getWeight())
                .height(userParam.getHeight())
                .build();
    }
}
