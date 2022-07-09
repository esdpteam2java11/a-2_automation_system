package com.a2.a2_automation_system.util;

import com.a2.a2_automation_system.schedule.Schedule;
import com.a2.a2_automation_system.schedule.ScheduleRepository;

import com.a2.a2_automation_system.tariff.SportsmanPaymentRepository;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Configuration
public class InitDatabase {
    private final PasswordEncoder passwordEncoder;

    public InitDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, GroupRepository groupRepository, ParentRepository parentRepository, ScheduleRepository scheduleRepository, SportsmanPaymentRepository sportsmanPaymentRepository) {
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
            for (int i = 0; i < 20; i++) {
                if (userRepository.findByLogin("student_" + i).isEmpty()) {
                    User student = new User();
                    student.setSurname("studentSurname_" + i);
                    student.setName("studentName_" + i);
                    student.setPatronymic("studentPatronymic_" + i);
                    student.setPhone("33-33-33");
                    student.setAddress("г. Бишкек, ул. Ахунбаева 26");
                    student.setBirthDate(new Date());
                    student.setRole(Role.CLIENT);
                    student.setLogin("student_" + i);
                    student.setPassword(passwordEncoder.encode("123"));
                    student.setIsActive(true);
                    userRepository.save(student);
                }
            }
            if (groupRepository.findAll().isEmpty()) {
                List<Group> groups = List.of(
                        Group.builder().name("Детская группа 1").sum(3000).trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Детская группа 2").sum(3000).trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Младшая группа").sum(1000).trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Старшая группа").sum(3000).trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Взрослая группа").sum(2000).trainer(userRepository.findByLogin("manager").orElse(null)).build());
                groupRepository.saveAll(groups);
            }

        };
    }
}
