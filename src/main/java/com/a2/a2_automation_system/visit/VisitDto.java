package com.a2.a2_automation_system.visit;

import com.a2.a2_automation_system.schedule.Schedule;
import com.a2.a2_automation_system.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {

    private Long id;

    @JsonProperty("student_id")
    private User student;

    @JsonProperty("schedule_id")
    private Schedule schedule;

    public static VisitDto from(Visit visit) {
        return VisitDto.builder()
                .id(visit.getId())
                .student(visit.getStudent())
                .schedule(visit.getSchedule())
                .build();
    }
}
