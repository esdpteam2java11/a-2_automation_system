package com.a2.a2_automation_system.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRestDto {
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String color;
    private String url;
    private String textColor;

    public static ScheduleRestDto from(Schedule schedule){
        LocalDateTime start = LocalDateTime.of(schedule.getEventDate(),schedule.getStartTime());
        LocalDateTime end = LocalDateTime.of(schedule.getEventDate(),schedule.getEndTime());
        return builder()
                .title(schedule.getGroup().getName())
                .start(start)
                .end(end)
                .color("red")
                .url("/event")
                .textColor("red")
                .build();
    }

}
