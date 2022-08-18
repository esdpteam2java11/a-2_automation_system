package com.a2.a2_automation_system.schedule;

import com.a2.a2_automation_system.group.Group;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.*;


@SpringBootTest(classes = {Schedule.class})
@ExtendWith(SpringExtension.class)
public class ScheduleServiceTest {
    @InjectMocks
    private ScheduleService scheduleService;


    @Mock
    private ScheduleRepository scheduleRepository;

    private Schedule schedule;



    @BeforeEach
    void setupBeforeEach() {
        schedule = Schedule.builder()
                .endTime(LocalTime.now())
                .eventDate(LocalDate.now())
                .group(new Group())
                .startTime(LocalTime.now())
                .uniqueIdForSerialEvent("")
                .trainingProgram("")
                .build();
    }
    @Test
    void addEvent() {
        when(scheduleRepository.save(ArgumentMatchers.any(Schedule.class))).thenReturn(schedule);
         scheduleService.addEventFromScheduleDTO(ScheduleDTO.from(schedule));

    }

    @Test
    void getEventById() {

        when(scheduleRepository.getById(schedule.getId())).thenReturn(schedule);
        ScheduleDTO oneSchedule = scheduleService.getEventById(schedule.getId());
        Assertions.assertThat(schedule.getId()).isEqualTo(oneSchedule.getId());
        Assertions.assertThat(schedule.getEventDate()).isEqualTo(oneSchedule.getEventDate());
        Assertions.assertThat(schedule.getGroup()).isEqualTo(oneSchedule.getGroup());
        Assertions.assertThat(schedule.getStartTime()).isEqualTo(oneSchedule.getStartTime());
        Assertions.assertThat(schedule.getEndTime()).isEqualTo(oneSchedule.getEndTime());
        Assertions.assertThat(schedule.getTrainingProgram()).isEqualTo(oneSchedule.getTrainingProgram());
        Assertions.assertThat(schedule.getUniqueIdForSerialEvent()).isEqualTo(oneSchedule.getUniqueIdForSerial());

    }



    @Test
    void deleteById() {
        Schedule schedule1 = new Schedule();
        schedule1.setId(2L);
        schedule1.setEndTime(LocalTime.now());
        schedule1.setStartTime(LocalTime.now());
        schedule1.setGroup(new Group());
        schedule1.setEventDate(LocalDate.now());
        schedule1.setTrainingProgram("2");
        schedule1.setUniqueIdForSerialEvent("1");
        when(scheduleRepository.getById(schedule1.getId())).thenReturn(schedule1);
        scheduleService.deleteEventById(schedule1.getId());
    }

    @Test
    void deleteEventsInSeries() {
        Schedule schedule1 = new Schedule();
        schedule1.setId(2L);
        schedule1.setEndTime(LocalTime.now());
        schedule1.setStartTime(LocalTime.now());
        schedule1.setGroup(new Group());
        schedule1.setEventDate(LocalDate.now());
        schedule1.setTrainingProgram("2");
        schedule1.setUniqueIdForSerialEvent("1");

        when(scheduleRepository.getById(schedule1.getId())).thenReturn(schedule1);
        var scheduleList = scheduleRepository.getAllByUniqueIdForSerialEventAndEventDateIsGreaterThanEqual(schedule1.getUniqueIdForSerialEvent(),schedule1.getEventDate());
        if(scheduleList.isPresent()){
            for(Schedule ev:scheduleList.get()){
                scheduleService.deleteEventsInSeries(schedule1.getId());
            }
        }

    }



}
