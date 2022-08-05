package com.a2.a2_automation_system.sportmancabinet;

import com.a2.a2_automation_system.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanEventsDTO {

    private Long id;
    private User sportsman;
    private LocalDate eventDate;
    private String trainingProgram;
    private String food;
    private String uniqueIdForSerialEvent;
    private String title;

    public static SportsmanEventsDTO from(SportsmanEvents sportsmanEvents){
       return builder()
                .id(sportsmanEvents.getId())
                .sportsman(sportsmanEvents.getSportsman())
                .eventDate(sportsmanEvents.getEventDate())
                .title(sportsmanEvents.getTitle())
                .trainingProgram(sportsmanEvents.getTrainingProgram())
                .food(sportsmanEvents.getFood())
                .uniqueIdForSerialEvent(sportsmanEvents.getUniqueIdForSerialEvent())
                .build();
    }
}
