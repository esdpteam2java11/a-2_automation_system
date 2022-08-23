package com.a2.a2_automation_system.sportmancabinet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanEventsRestDTO {
    private Long id;
    private String title;
    private LocalDate start;
    private String color;
    private String url;
    private Boolean allDay;

    public static SportsmanEventsRestDTO from(SportsmanEvents sportsmanEvents) {
        return builder()
                .id(sportsmanEvents.getId())
                .title(sportsmanEvents.getTitle())
                .start(sportsmanEvents.getEventDate())
                .allDay(true)
                .url("event/" + sportsmanEvents.getId())
                .color("blue")
                .build();
    }

}
