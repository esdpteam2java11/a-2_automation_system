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
    private String backgroundColor;
    private Boolean displayEventTime;

    public static ScheduleRestDto from(Schedule schedule){
        LocalDateTime start = LocalDateTime.of(schedule.getEventDate(),schedule.getStartTime());
        LocalDateTime end = LocalDateTime.of(schedule.getEventDate(),schedule.getEndTime());
        String eventUrl = String.format("/group/%s/calendar/event/%s",schedule.getGroup().getId(),schedule.getId());
        return builder()
                .title(schedule.getGroup().getName())
                .start(start)
                .end(end)
                .color(schedule.getGroup().getColor())
                .url(eventUrl)
                .textColor(schedule.getGroup().getColor())
                .backgroundColor(schedule.getGroup().getColor())
                .displayEventTime(true)
                .build();
    }


}
