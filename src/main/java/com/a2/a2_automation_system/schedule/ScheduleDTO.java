package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.group.Group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @NotNull
    private Group group;

    private String uniqueIdForSerial;

    public static ScheduleDTO from(Schedule schedule) {
        return builder()
                .id(schedule.getId())
                .eventDate(schedule.getEventDate())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .group(schedule.getGroup())
                .uniqueIdForSerial(schedule.getUniqueIdForSerialEvent())
                .build();
    }
}
