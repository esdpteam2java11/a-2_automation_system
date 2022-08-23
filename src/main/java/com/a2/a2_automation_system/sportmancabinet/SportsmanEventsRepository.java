package com.a2.a2_automation_system.sportmancabinet;

import com.a2.a2_automation_system.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SportsmanEventsRepository extends JpaRepository<SportsmanEvents, Long> {

    List<SportsmanEvents> getSportsmanEventsBySportsmanAndEventDateBetween(User user, LocalDate startDate, LocalDate endDate);

    SportsmanEvents getSportsmanEventsById(Long id);

    Optional<List<SportsmanEvents>> getAllByUniqueIdForSerialEventAndEventDateIsGreaterThanEqual(String id, LocalDate eventDate);

}
