package com.a2.a2_automation_system.users;

import com.a2.a2_automation_system.commons.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByLogin(String email);
    Page<User> findAll(Pageable pageable);

    Page<User> findAllByRole(Pageable pageable, Role role);

    Page<User> findAllByIsActive(Pageable pageable, boolean isActive);

    Page<User> findAllByIsActiveAndRole(Pageable pageable, boolean isActive, Role role);


}
