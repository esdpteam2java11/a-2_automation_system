package com.a2.a2_automation_system.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private void addEvent(ScheduleDTO scheduleDTO){
        Schedule schedule = Schedule.builder()
                .eventDate(scheduleDTO.getEventDate())
                .startTime(scheduleDTO.getStartTime())
                .endTime(scheduleDTO.getEndTime())
                .group(scheduleDTO.getGroup())
                .build();
        scheduleRepository.save(schedule);
    }
}
