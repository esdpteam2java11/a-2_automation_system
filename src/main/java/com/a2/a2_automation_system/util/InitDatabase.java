package com.a2.a2_automation_system.util;

import com.a2.a2_automation_system.commons.Role;
import com.a2.a2_automation_system.users.User;
import com.a2.a2_automation_system.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
public class InitDatabase {
    private final PasswordEncoder passwordEncoder;

    public InitDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return (args) -> {
            if (userRepository.findByLogin("admin1").isEmpty()) {
                User admin = new User();
                admin.setSurname("admin");
                admin.setName("admin");
                admin.setPhone("22-22-22");
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
                student.setBirthDate(new Date());
                student.setRole(Role.CLIENT);
                student.setFather(userRepository.findByLogin("parent").get());
                student.setLogin("student");
                student.setPassword(passwordEncoder.encode("123"));
                student.setIsActive(true);
                userRepository.save(student);
            }
        };
    }
}
