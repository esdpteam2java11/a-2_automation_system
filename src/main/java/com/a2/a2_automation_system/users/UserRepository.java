package com.a2.a2_automation_system.users;

import com.a2.a2_automation_system.commons.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String email);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByRole(Pageable pageable, Role role);

    Page<User> findAllByIsActive(Pageable pageable, boolean isActive);

    Page<User> findAllByIsActiveAndRole(Pageable pageable, boolean isActive, Role role);


}
