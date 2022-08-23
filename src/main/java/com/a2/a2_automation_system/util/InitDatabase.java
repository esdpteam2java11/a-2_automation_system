package com.a2.a2_automation_system.util;

import com.a2.a2_automation_system.sportsmanpayments.OperationType;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentRepository;
import com.a2.a2_automation_system.user.Role;
import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserRepository;
import com.a2.a2_automation_system.userparam.UserParam;
import com.a2.a2_automation_system.userparam.UserParamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
public class InitDatabase {
    private final PasswordEncoder passwordEncoder;

    public InitDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, GroupRepository groupRepository,
                           SportsmanPaymentRepository sportsmanPaymentRepository,
                           UserParamRepository userParamRepository) {
        return (args) -> {
            if (userRepository.findByLogin("admin").isEmpty()) {
                User admin = new User();
                admin.setSurname("admin");
                admin.setName("admin");
                admin.setPhone("000000");
                admin.setAddress("г. Бишкек");
                admin.setRole(Role.ADMIN);
                admin.setLogin("admin");
                admin.setPassword(passwordEncoder.encode("123"));
                admin.setIsActive(true);
                userRepository.save(admin);
            }
            if (userRepository.findByLogin("arsen").isEmpty()) {
                User employee = new User();
                employee.setSurname("Камчибеков");
                employee.setName("Арсен");
                employee.setPhone("000000");
                employee.setAddress("г. Бишкек");
                employee.setRole(Role.EMPLOYEE);
                employee.setLogin("arsen");
                employee.setPassword(passwordEncoder.encode("123"));
                employee.setIsActive(true);
                userRepository.save(employee);
            }
            List<Group> groups = groupRepository.findAll();
            if (groups.size() == 0) {
                groups = List.of(
                        Group.builder().name("Детская группа 1").sum(3000)
                                .trainer(userRepository.findByLogin("arsen")
                                        .orElse(null)).color("#e03434").build(),
                        Group.builder().name("Детская группа 2").sum(3000)
                                .trainer(userRepository.findByLogin("arsen")
                                        .orElse(null)).color("#e034c4").build(),
                        Group.builder().name("Младшая группа").sum(1000)
                                .trainer(userRepository.findByLogin("arsen")
                                        .orElse(null)).color("#9e34e0").build(),
                        Group.builder().name("Старшая группа").sum(3000)
                                .trainer(userRepository.findByLogin("arsen")
                                        .orElse(null)).color("#3634e0").build(),
                        Group.builder().name("Взрослая группа").sum(2000)
                                .trainer(userRepository.findByLogin("arsen")
                                        .orElse(null)).color("#34e059").build());
                groupRepository.saveAll(groups);
            }
            for (int i = 0; i < 19; i++) {
                if (userRepository.findByLogin("student_" + i).isEmpty()) {
                    User student = new User();
                    student.setSurname("Фамилия_" + i);
                    student.setName("Имя_" + i);
                    student.setPatronymic("Отчество_" + i);
                    student.setPhone("33-33-33");
                    student.setAddress("г. Бишкек, ул. Ахунбаева 26");
                    student.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2010"));
                    student.setRole(Role.CLIENT);
                    student.setLogin("student_" + i);
                    student.setPassword(passwordEncoder.encode("123"));
                    student.setIsActive(true);
                    student.setGroup(groups.get((int) (Math.random() * groups.size())));
                    student.setDateOfAdmission(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2022"));
                    userRepository.save(student);

                    SportsmanPayment sportsmanPayment = new SportsmanPayment();
                    sportsmanPayment.setAmount((double) student.getGroup().getSum());
                    sportsmanPayment.setDate(student.getDateOfAdmission());
                    sportsmanPayment.setUser(student);
                    sportsmanPayment.setOperationType(OperationType.ACCRUED);
                    sportsmanPaymentRepository.save(sportsmanPayment);

                    userParamRepository.save(UserParam.builder()
                            .height(165.0)
                            .weight(65.0)
                            .creationDate(new Date())
                            .user(student)
                            .build());
                }
            }
        };
    }
}
