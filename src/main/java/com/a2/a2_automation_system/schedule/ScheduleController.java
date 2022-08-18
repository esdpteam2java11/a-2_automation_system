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
    public String getScheduleElement(@PathVariable Long groupId, @PathVariable Long eventId, Model model) {


        model.addAttribute("group", groupService.getGroupById(groupId));
        try{
            model.addAttribute("event", scheduleService.getEventById(eventId));

        }catch (Exception e){
            return "No_Found";
        }
        model.addAttribute("users", groupService.getUsersByGroup(groupId));
        model.addAttribute("visit", scheduleService.getUsersWhoCame(eventId));
        return "event";
    }
    @PostMapping("/group/{groupId}/calendar/event/{eventId}/trainingProgram")
    public String addTrainingProgram(@PathVariable Long groupId,@PathVariable Long eventId, @RequestParam String content,RedirectAttributes redirectAttributes){
        String message = scheduleService.addEventTrainingProgram(content,eventId);
        String pathRedirect = String.format("redirect:/group/%s/calendar/event/%s",groupId,eventId);
        redirectAttributes.addFlashAttribute("programMessage",message);
        return pathRedirect;

    }

    @GetMapping("/group/{groupId}/calendar/event/{eventId}/delete")
    public String deleteScheduleElement(@PathVariable Long groupId, @PathVariable Long eventId, RedirectAttributes redirectAttributes) {
        String pathRedirect = String.format("redirect:/group/%s/calendar", groupId);
        try{
            ScheduleDTO event = scheduleService.deleteEventById(eventId);
            String message = String.format("Удалено занятие для %s на %s", event.getGroup().getName(), event.getEventDate());
            redirectAttributes.addFlashAttribute("message", message);
        }catch (RuntimeException e){
            String error ="Невозможно удалить занятие с отметками о посещении!";
            redirectAttributes.addFlashAttribute("error",error);
        }

        return pathRedirect;
    }

    @GetMapping("/group/{groupId}/calendar/event/{eventId}/deleteConnected")
    public String deleteScheduleElements(@PathVariable Long groupId, @PathVariable Long eventId, RedirectAttributes redirectAttributes) {
        String pathRedirect = String.format("redirect:/group/%s/calendar", groupId);

        try{
        ScheduleDTO event = scheduleService.deleteEventsInSeries(eventId);
        String message = String.format("Удалены занятия для %s с %s", event.getGroup().getName(), event.getEventDate());
        redirectAttributes.addFlashAttribute("message", message);
        }catch (RuntimeException e){
            String error ="Невозможно удалить занятие с отметками о посещении!";
            redirectAttributes.addFlashAttribute("error",error);
        }
        return pathRedirect;
    }

    @PostMapping("/group/{groupId}/calendar/event/{eventId}/edit")
    public String editScheduleElement(@PathVariable Long groupId, @PathVariable Long eventId, RedirectAttributes redirectAttributes,
                                      @Valid ScheduleCreateDTO scheduleCreateDTO, BindingResult result) {
        String pathRedirect = String.format("redirect:/group/%s/calendar/event/%s", groupId, eventId);
        if (scheduleCreateDTO.getTimeStart().isAfter(scheduleCreateDTO.getTimeEnd())) {
            redirectAttributes.addFlashAttribute("errorTime", "Время не правильно");
            return pathRedirect;
        }
        scheduleService.editEvent(scheduleCreateDTO);
        redirectAttributes.addFlashAttribute("message", "Отредактрировано");
        return pathRedirect;
    }

    @GetMapping("/group/{id}/calendar/events/create")
    public String getSchedule(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "add_schedule";
    }

    @PostMapping("/group/{id}/calendar/events/create")
    public String createEvent(@PathVariable String id, Model model, @Valid ScheduleCreateDTO scheduleCreateDTO, BindingResult result, RedirectAttributes attributes) {
        String pathRedirect = String.format("redirect:/group/%s/calendar", id);
        if (scheduleCreateDTO.getTimeStart().isAfter(scheduleCreateDTO.getTimeEnd())) {
            attributes.addFlashAttribute("errorTime", "Время не правильно");
            return "redirect:/group/" + id + "/calendar/events/create";
        }
        if (result.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", result.getFieldErrors());
            return "redirect:/group/" + id + "/calendar/events/create";
        }
        if (scheduleCreateDTO.getRecurring() != null) {
            if (scheduleCreateDTO.getEventStartDate().isAfter(scheduleCreateDTO.getDateEnd()) ||
                    scheduleCreateDTO.getEventStartDate().isEqual(scheduleCreateDTO.getDateEnd())) {
                attributes.addFlashAttribute("errorDate", "Дата конца неправильна");
                return "redirect:/group/" + id + "/calendar/events/create";
            }
            if (scheduleCreateDTO.getDayOfWeek() == null) {
                attributes.addFlashAttribute("errorDayOfWeek", "Выберите хотя бы один день недели");
                return "redirect:/group/" + id + "/calendar/events/create";
            }

        }
        scheduleService.addEventsFromScheduleCreateDto(scheduleCreateDTO);
        return pathRedirect;
    }


}
