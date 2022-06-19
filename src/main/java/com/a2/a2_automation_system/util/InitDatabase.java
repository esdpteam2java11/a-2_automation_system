package com.a2.a2_automation_system.util;

import com.a2.a2_automation_system.common.Role;
import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.parent.Kinship;
import com.a2.a2_automation_system.parent.Parent;
import com.a2.a2_automation_system.parent.ParentRepository;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class InitDatabase {
    private final PasswordEncoder passwordEncoder;

    public InitDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, GroupRepository groupRepository, ParentRepository parentRepository) {
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
            if (userRepository.findByLogin("parent").isEmpty()) {
                User parent = new User();
                parent.setSurname("parentSurname");
                parent.setName("parentName");
                parent.setPhone("55-55-55");
                parent.setAddress("г. Бишкек, ул. Советская 26");
                parent.setRole(Role.CLIENT);
                parent.setLogin("parent");
                parent.setPassword(passwordEncoder.encode("123"));
                parent.setIsActive(true);
                userRepository.save(parent);
            }
            if (userRepository.findByLogin("student").isEmpty()) {
                User student = new User();
                student.setSurname("studentSurname");
                student.setName("studentName");
                student.setPhone("33-33-33");
                student.setAddress("г. Бишкек, ул. Ахунбаева 26");
                student.setBirthDate(new Date());
                student.setRole(Role.CLIENT);
                student.setLogin("student");
                student.setPassword(passwordEncoder.encode("123"));
                student.setIsActive(true);
                userRepository.save(student);
            }
            if (groupRepository.findAll().isEmpty()) {
                List<Group> groups = List.of(
                        Group.builder().name("Детская группа 1").trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Детская группа 2").trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Младшая группа").trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Старшая группа").trainer(userRepository.findByLogin("manager").orElse(null)).build(),
                        Group.builder().name("Взрослая группа").trainer(userRepository.findByLogin("manager").orElse(null)).build());
                        groupRepository.saveAll(groups);
            }

            for (int i = 0; i < 10; i++) {
                if (parentRepository.findBySurname("Parent_"+i).isEmpty()) {
                    Parent parent = new Parent();
                    parent.setSurname("Parent_"+i);
                    parent.setName("Parent_"+i);
                    parent.setPatronymic("Parent_"+i);
                    parent.setKinship(Kinship.MOTHER);
                    parent.setPhone("33-33-33");
                    parent.setTelegram("55-55-55");
                    parent.setWhatsapp("66-66-66");
                    parentRepository.save(parent);
                }
            }
        };
    }
}
