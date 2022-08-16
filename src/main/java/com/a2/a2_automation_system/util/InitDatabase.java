package com.a2.a2_automation_system.util;

import com.a2.a2_automation_system.schedule.ScheduleRepository;

import com.a2.a2_automation_system.sportsmanpayments.OperationType;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentRepository;
import com.a2.a2_automation_system.user.Role;
import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.parent.ParentRepository;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class InitDatabase {
    private final PasswordEncoder passwordEncoder;

    public InitDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, GroupRepository groupRepository,
                           ScheduleRepository scheduleRepository, SportsmanPaymentRepository sportsmanPaymentRepository) {
        return (args) -> {
            if (userRepository.findByLogin("admin1").isEmpty()) {
                User admin = new User();
                admin.setSurname("admin");
                admin.setName("admin");
                admin.setPhone("22-22-22");
                admin.setAddress("г. Бишкек, ул. Московская 25");
                admin.setRole(Role.ADMIN);
                admin.setLogin("admin1");
                admin.setPassword(passwordEncoder.encode("123"));
                admin.setIsActive(true);
                userRepository.save(admin);
            }
            if (userRepository.findByLogin("manager").isEmpty()) {
                User employee = new User();
                employee.setSurname("managerSurname");
                employee.setName("managerName");
                employee.setPhone("22-22-22");
                employee.setAddress("г. Бишкек, ул. Исанова 26");
                employee.setRole(Role.EMPLOYEE);
                employee.setLogin("manager");
                employee.setPassword(passwordEncoder.encode("123"));
                employee.setIsActive(true);
                userRepository.save(employee);
            }
            List<Group> groups = groupRepository.findAll();
            if (groups.size() == 0) {
                groups = List.of(
                        Group.builder().name("Детская группа 1").sum(3000)
                                .trainer(userRepository.findByLogin("manager").orElse(null)).color("#e03434").build(),
                        Group.builder().name("Детская группа 2").sum(3000)
                                .trainer(userRepository.findByLogin("manager").orElse(null)).color("#e034c4").build(),
                        Group.builder().name("Младшая группа").sum(1000)
                                .trainer(userRepository.findByLogin("manager").orElse(null)).color("#9e34e0").build(),
                        Group.builder().name("Старшая группа").sum(3000)
                                .trainer(userRepository.findByLogin("manager").orElse(null)).color("#3634e0").build(),
                        Group.builder().name("Взрослая группа").sum(2000)
                                .trainer(userRepository.findByLogin("manager").orElse(null)).color("#34e059").build());
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
                    student.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2022"));
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
                }
            }
        };
    }
}
