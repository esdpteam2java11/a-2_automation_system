package com.a2.a2_automation_system.sportsmancabinet;



import com.a2.a2_automation_system.news.News;

import com.a2.a2_automation_system.sportmancabinet.SportsmanEvents;
import com.a2.a2_automation_system.sportmancabinet.SportsmanEventsDTO;
import com.a2.a2_automation_system.sportmancabinet.SportsmanEventsRepository;
import com.a2.a2_automation_system.sportmancabinet.SportsmanEventsService;
import com.a2.a2_automation_system.user.User;
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

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SportsmanEvents.class})
@ExtendWith(SpringExtension.class)
public class SportsmanEventsServiceTest {

    @InjectMocks
    private SportsmanEventsService sportsmanEventsService;


    @Mock
    private SportsmanEventsRepository sportsmanEventsRepository;

    private SportsmanEvents sportsmanEvents;


    @BeforeEach
    void setupBeforeEach() {
        sportsmanEvents = SportsmanEvents.builder()
                .eventDate(LocalDate.now())
                .sportsman(new User())
                .uniqueIdForSerialEvent("")
                .trainingProgram("")
                .food("")
                .title("")
                .build();
    }
    @Test
    void addEvent() {
        when(sportsmanEventsRepository.save(ArgumentMatchers.any(SportsmanEvents.class))).thenReturn(sportsmanEvents);
        sportsmanEventsService.addEventFromSportsmanEventsDTO(SportsmanEventsDTO.from(sportsmanEvents));

    }

    @Test
    void getEventById() {

        when(sportsmanEventsRepository.getSportsmanEventsById(sportsmanEvents.getId())).thenReturn(sportsmanEvents);
        SportsmanEventsDTO oneEvent = sportsmanEventsService.getEventById(sportsmanEvents.getId());
        Assertions.assertThat(sportsmanEvents.getId()).isEqualTo(oneEvent.getId());
        Assertions.assertThat(sportsmanEvents.getEventDate()).isEqualTo(oneEvent.getEventDate());
        Assertions.assertThat(sportsmanEvents.getSportsman()).isEqualTo(oneEvent.getSportsman());
        Assertions.assertThat(sportsmanEvents.getFood()).isEqualTo(oneEvent.getFood());
        Assertions.assertThat(sportsmanEvents.getTitle()).isEqualTo(oneEvent.getTitle());
        Assertions.assertThat(sportsmanEvents.getTrainingProgram()).isEqualTo(oneEvent.getTrainingProgram());
        Assertions.assertThat(sportsmanEvents.getUniqueIdForSerialEvent()).isEqualTo(oneEvent.getUniqueIdForSerialEvent());

    }


    @Test
    void deleteById() {
        SportsmanEvents sportsmanEvents1 = new SportsmanEvents();
        sportsmanEvents1.setId(2L);
        sportsmanEvents1.setFood("2");
        sportsmanEvents1.setTitle("1");
        sportsmanEvents1.setSportsman(new User());
        sportsmanEvents1.setEventDate(LocalDate.now());
        sportsmanEvents1.setTrainingProgram("2");
        sportsmanEvents1.setUniqueIdForSerialEvent("1");
        when(sportsmanEventsRepository.getSportsmanEventsById(sportsmanEvents1.getId())).thenReturn(sportsmanEvents1);
        sportsmanEventsService.deleteEventById(sportsmanEvents1.getId());
    }



    @Test
    void deleteEventsInSeries() {
        SportsmanEvents sportsmanEvents1 = new SportsmanEvents();
        sportsmanEvents1.setId(2L);
        sportsmanEvents1.setFood("2");
        sportsmanEvents1.setTitle("1");
        sportsmanEvents1.setSportsman(new User());
        sportsmanEvents1.setEventDate(LocalDate.now());
        sportsmanEvents1.setTrainingProgram("2");
        sportsmanEvents1.setUniqueIdForSerialEvent("1");

        when(sportsmanEventsRepository.getById(sportsmanEvents1.getId())).thenReturn(sportsmanEvents1);
        var eventsList = sportsmanEventsRepository.getAllByUniqueIdForSerialEventAndEventDateIsGreaterThanEqual(sportsmanEvents1.getUniqueIdForSerialEvent(),sportsmanEvents1.getEventDate());
        if(eventsList.isPresent()){
            for(SportsmanEvents ev:eventsList.get()){
                sportsmanEventsService.deleteEventsInSeries(sportsmanEvents1.getId());
            }
        }

    }


}
