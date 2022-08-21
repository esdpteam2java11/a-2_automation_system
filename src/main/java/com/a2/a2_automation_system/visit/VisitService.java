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
import java.util.*;
import java.util.stream.Collectors;

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
        List<Visit> visitList = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();
        Comparator<Visit> sortDesc = (visit1, visit2) -> visit2.getSchedule().getEventDate().compareTo(visit1.getSchedule().getEventDate());
        var listVisit = visitRepository.findAllByStudent(student);
        if(listVisit.isPresent()&&listVisit.get().size()>0) {
            visitList = listVisit.get().stream().filter(visit -> visit.getSchedule().getEventDate().isBefore(dateNow)).collect(Collectors.toList());
            Collections.sort(visitList, sortDesc);
            return Optional.of(visitList);
        }
        return listVisit;
    }

}
