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
public class ScheduleRestDtoForSportsman {
    private Long id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String color;
    private Boolean allDay;
    private String textColor;

    public static ScheduleRestDtoForSportsman from(Schedule schedule){
        LocalDateTime start = LocalDateTime.of(schedule.getEventDate(),schedule.getStartTime());
        LocalDateTime end = LocalDateTime.of(schedule.getEventDate(),schedule.getEndTime());
        String title = String.format("%s %s",schedule.getStartTime(),schedule.getGroup().getName());
         return builder()
                .id(schedule.getId())
                .title(title)
                .start(start)
                .end(end)
                .color(schedule.getGroup().getColor())
                .allDay(true)
                .build();
    }

}
