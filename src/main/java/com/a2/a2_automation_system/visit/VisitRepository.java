package com.a2.a2_automation_system.visit;

import com.a2.a2_automation_system.schedule.Schedule;
import com.a2.a2_automation_system.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {

    boolean existsByStudentAndSchedule(User user, Schedule schedule);

    Visit findByStudentAndSchedule(User user, Schedule schedule);

    List<Visit> findAllByScheduleId(Long eventId);
    List<Visit> getAllByStudent(User student);
    Optional<List<Visit>> findAllByStudent(User user);

}
