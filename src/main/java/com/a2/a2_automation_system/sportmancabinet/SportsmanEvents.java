package com.a2.a2_automation_system.sportmancabinet;

import com.a2.a2_automation_system.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "sportsman_events")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportsmanEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sportsman_id")
    private User sportsman;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "training_program")
    private String trainingProgram;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "food")
    private String food;

    @Column(name = "unique_id_for_serial_event")
    private String uniqueIdForSerialEvent;

}
