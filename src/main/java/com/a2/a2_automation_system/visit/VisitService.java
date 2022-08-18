package com.a2.a2_automation_system.visit;

import com.a2.a2_automation_system.exception.UserNotFoundException;
import com.a2.a2_automation_system.schedule.Schedule;
import com.a2.a2_automation_system.schedule.ScheduleDTO;
import com.a2.a2_automation_system.schedule.ScheduleRepository;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;


    public void noteTheVisit(Long id, ScheduleDTO scheduleDTO) {
        Schedule byEventDate = scheduleRepository.findById(scheduleDTO.getId()).orElseThrow();
        User student = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Такого студента нет"));
        if(visitRepository.existsByStudentAndSchedule(student, byEventDate)) {
            Visit byId = visitRepository.findByStudentAndSchedule(student, byEventDate);
            visitRepository.delete(byId);
            return;
        }
        visitRepository.save(Visit.builder()
                .student(student)
                .schedule(byEventDate)
                .build());
    }

    public Optional<List<Visit>> getLatestVisit(User student){
        Comparator<Visit> sortDesc = (visit1, visit2) -> visit2.getSchedule().getEventDate().compareTo(visit1.getSchedule().getEventDate());
        var listVisit = visitRepository.findAllByStudent(student);
        if(listVisit.isPresent()) {
            Collections.sort(listVisit.get(), sortDesc);
            return listVisit;
        }
        return listVisit;
    }

}
