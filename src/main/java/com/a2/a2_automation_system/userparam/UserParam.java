package com.a2.a2_automation_system.userparam;

import com.a2.a2_automation_system.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "user_params")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
//    @Size(min = 10, message = "Вес должен быть больше или равно 10")
    private Double weight;

    @NotNull
//    @Size(min = 20, message = "Рост должен быть больше или равно 20")
    private Double height;


}
