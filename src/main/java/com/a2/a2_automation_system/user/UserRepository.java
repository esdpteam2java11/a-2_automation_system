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

    Page<User> findAll(Pageable pageable);

    List<User> findAll();

    Page<User> findAllByRole(Pageable pageable, Role role);

    Page<User> findAllByIsActive(Pageable pageable, boolean isActive);

    Page<User> findAllByIsActiveAndRole(Pageable pageable, boolean isActive, Role role);

    boolean existsByLogin(String login);

    List<User> findByRole(Role role);

    @Query(value = "SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :search,'%')) " +
            " OR LOWER(u.surname) LIKE LOWER(CONCAT('%', :search,'%')) " +
            " OR LOWER(u.patronymic) LIKE LOWER(CONCAT('%', :search,'%'))")
    Page<User> findUserByNameOrSurnameOrPatronymic(Pageable pageable, @Param("search") String search);

    @Query("select u FROM User u where u.group.id= :id")
    List<User> findByGroup(Long id);

}
