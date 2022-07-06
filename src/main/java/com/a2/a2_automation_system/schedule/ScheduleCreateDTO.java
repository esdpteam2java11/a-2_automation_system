package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.exception.DateEnteredNotCorrect;
import com.a2.a2_automation_system.group.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleCreateDTO {

    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventStartDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeStart;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeEnd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    private String[] dayOfWeek;

    private String recurring;

    @NotNull
    private Group group;

    public void eventStartDate(LocalDate date) {
        if(date.isAfter(LocalDate.now())) {
            eventStartDate = date;
        } else {
            throw new DateEnteredNotCorrect("Введите правильную дату");
        }
    }

}
