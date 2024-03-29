package com.a2.a2_automation_system.sportmancabinet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanEventCreateDTO {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    private String[] dayOfWeek;

    private String recurring;

    private String title;

}
