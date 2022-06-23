package com.a2.a2_automation_system.tariff;

import com.a2.a2_automation_system.group.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tariffs")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tariff {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "start_date")
        private Date startDate;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "group_id")
        private Group group;

        @NotNull
        private Double amount;

}
