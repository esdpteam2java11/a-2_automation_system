package com.a2.a2_automation_system.visit;

import com.a2.a2_automation_system.schedule.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VisitRestController {
    private final VisitService visitService;

    @PostMapping("/note/{id}")
    public void noteTheVisit(@PathVariable(value = "id") String id, @RequestBody ScheduleDTO schedule) {
        visitService.noteTheVisit(Long.valueOf(id), schedule);
    }

}
