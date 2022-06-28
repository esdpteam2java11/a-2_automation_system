package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.group.Group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("start_date")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("end_date")
    private Date endDate;

    @NotNull
    @JsonProperty("group_id")
    private Group group;

    public static ScheduleDTO from(Schedule schedule) {
        return builder()
                .id(schedule.getId())
                .endDate(schedule.getEndDate())
                .startDate(schedule.getStartDate())
                .group(schedule.getGroup())
                .build();

    }
}
