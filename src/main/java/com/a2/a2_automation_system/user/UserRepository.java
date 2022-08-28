package com.a2.a2_automation_system.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByLogin(String email);

    @Query(value = "select u from User u where u.role <> 'ADMIN'")
    Page<User> findAllExceptAdmin(Pageable pageable);

    List<User> findAllByRole(Role role);

    @Query(value = "select u from User u where u.role <> 'ADMIN' and u.role= :role")
    Page<User> findAllByRoleExceptAdmin(Pageable pageable, Role role);

    @Query(value = "select u from User u where u.role <> 'ADMIN' and " +
            " u.isActive = :isActive")
    Page<User> findAllByIsActive(Pageable pageable, @Param("isActive") boolean isActive);

    Page<User> findAllByIsActiveAndRole(Pageable pageable, boolean isActive, Role role);

    boolean existsByLogin(String login);

    List<User> findByRole(Role role);

    @Query(value = "SELECT u FROM User u WHERE u.role <> 'ADMIN' AND (LOWER(u.name) LIKE LOWER(CONCAT('%', :search,'%')) " +
            " OR LOWER(u.surname) LIKE LOWER(CONCAT('%', :search,'%')) " +
            " OR LOWER(u.patronymic) LIKE LOWER(CONCAT('%', :search,'%')))")
    Page<User> findUserByNameOrSurnameOrPatronymic(Pageable pageable, @Param("search") String search);

    @Query("select u FROM User u where u.group.id= :id")
    List<User> findByGroup(Long id);
}
