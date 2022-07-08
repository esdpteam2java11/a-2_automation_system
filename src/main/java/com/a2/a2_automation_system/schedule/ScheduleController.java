package com.a2.a2_automation_system.schedule;


import com.a2.a2_automation_system.group.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class ScheduleController {
    private final GroupService groupService;
    private final ScheduleService scheduleService;

    @GetMapping("/group/{groupId}/calendar/event/{eventId}")
    public String getScheduleElement(@PathVariable Long groupId,@PathVariable Long eventId,Model model){
        model.addAttribute("group",groupService.getGroupById(groupId));
        model.addAttribute("event",scheduleService.getEventById(eventId));
        return "event";
    }

    @GetMapping("/group/{id}/calendar/events/create")
    public String getSchedule(@PathVariable Long id, Model model){
        model.addAttribute("group", groupService.getGroupById(id));
        return "add_schedule";
    }

    @PostMapping("/group/{id}/calendar/events/create")
    public String createEvent(@PathVariable String id, Model model, @Valid ScheduleCreateDTO scheduleCreateDTO, BindingResult result, RedirectAttributes attributes){
        String pathRedirect = String.format("redirect:/group/%s/calendar",id);
        if(scheduleCreateDTO.getTimeStart().isAfter(scheduleCreateDTO.getTimeEnd())){
            attributes.addFlashAttribute("errorTime","Время не правильно");
            return "redirect:/group/"+id+"/calendar/events/create";
        }
        if(result.hasFieldErrors()){
            attributes.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/group/" +id+ "/calendar/events/create";
        }
        if(scheduleCreateDTO.getRecurring()!=null){
            if(scheduleCreateDTO.getEventStartDate().isAfter(scheduleCreateDTO.getDateEnd()) ||
                    scheduleCreateDTO.getEventStartDate().isEqual(scheduleCreateDTO.getDateEnd())){
                attributes.addFlashAttribute("errorDate","Дата конца неправильна");
                return "redirect:/group/"+id+"/calendar/events/create";
            }
            if(scheduleCreateDTO.getDayOfWeek()==null) {
                attributes.addFlashAttribute("errorDayOfWeek","Выберите хотя бы один день недели");
                return "redirect:/group/" + id + "/calendar/events/create";
            }

        }
        scheduleService.addEventsFromScheduleCreateDto(scheduleCreateDTO);
        return pathRedirect;
    }
}
