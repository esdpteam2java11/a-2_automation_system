package com.a2.a2_automation_system.schedule;


import com.a2.a2_automation_system.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s where s.group = ?1 and s.eventDate between ?2 and ?3")
    List<Schedule> getAllByGroupAndEventDateBetween(Group group,LocalDate startDate,LocalDate endDate);
    Schedule getById(Long id);
    Optional<List<Schedule>> getAllByUniqueIdForSerialEventAndEventDateIsGreaterThanEqual(String id,LocalDate startDate);
    List<Schedule> getSchedulesByEventDateBetween(LocalDate startDate,LocalDate endDate);
    List<Schedule> getAllByGroupAndEventDateIsLessThan(Group group,LocalDate localDate);
}
