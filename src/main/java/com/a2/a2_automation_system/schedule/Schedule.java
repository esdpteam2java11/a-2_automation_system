package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.group.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "schedules")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "event_date")
    private LocalDate eventDate;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "start_time")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "end_time")
    private LocalTime endTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}

