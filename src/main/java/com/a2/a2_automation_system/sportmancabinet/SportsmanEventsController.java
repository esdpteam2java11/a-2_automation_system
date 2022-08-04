package com.a2.a2_automation_system.sportmancabinet;

import com.a2.a2_automation_system.group.GroupDTO;
import com.a2.a2_automation_system.group.GroupService;

import com.a2.a2_automation_system.schedule.ScheduleDTO;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserDTO;
import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SportsmanEventsController {
    private final UserService userService;
    private final GroupService groupService;
    private final SportsmanEventsService sportsmanEventsService;

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/sportsman_cabinet/")
    public String getSportsmanPage(Model model, HttpServletRequest request){
        UserDTO user = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
        model.addAttribute("sportsman",user);
        return "sportsman_cabinet";
    }

    @GetMapping("/calendar_sportsman/all/")
    public String getAllCalendarForSportsman(Model model,HttpServletRequest request){
        UserDTO userDTO = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
        model.addAttribute("sportsman",userDTO);
        return "calendar_all_events_sportsman_view";
    }

    @GetMapping("/calendar_sportsman/{id}/")
    public String getAllCalendarForSportsman(Model model, @PathVariable String id, HttpServletRequest request){
        UserDTO userDTO = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
        GroupDTO groupDTO = groupService.getGroupById(Long.parseLong(id));
        model.addAttribute("group",groupDTO);
        model.addAttribute("sportsman",userDTO);
        return "calendar_for_sportsman";
    }
    @GetMapping("/calendar_sportsman/{id}/attendance/")
    public String getAttendanceCalendarForSportsman(Model model,@PathVariable String id,HttpServletRequest request){
        UserDTO userDTO = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
        GroupDTO groupDTO = groupService.getGroupById(Long.parseLong(id));
        model.addAttribute("group",groupDTO);
        model.addAttribute("sportsman",userDTO);
        return "calendar_for_sportsman_attendance";
    }

    @GetMapping("/sportsman_cabinet/create")
    public String getAddEventPageForSportsman(Model model,HttpServletRequest request, RedirectAttributes attributes){
        if(request.getRemoteUser()==null){
            attributes.addFlashAttribute("errorMessage", "Зайдите в систему");
            return "redirect:/login";
        }
        UserDTO userDTO = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
            model.addAttribute("sportsman", userDTO);
            return "add_event_for_sportsman";
    }

    @PostMapping("/sportsman_cabinet/create")
    public String addEventForSportsman(Model model, HttpServletRequest request, @Valid SportsmanEventCreateDTO sportsmanEventCreateDTO, BindingResult result, RedirectAttributes attributes){
        if (result.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", result.getFieldErrors());
            return "redirect:/sportsman_cabinet/create";
        }
        if (sportsmanEventCreateDTO.getRecurring() != null) {
            if (sportsmanEventCreateDTO.getDayOfWeek() == null) {
                attributes.addFlashAttribute("errorDayOfWeek", "Выберите хотя бы один день недели");
                return "redirect:/sportsman_cabinet/create";
            }
        }
        Optional<User> user = Optional.ofNullable(userService.getUserByUsername(request.getRemoteUser()));
        if(user.isPresent()){
        sportsmanEventsService.createEventsFromScheduleCreateDto(sportsmanEventCreateDTO,user.get());
        return "redirect:/sportsman_cabinet/";
        }else {
            attributes.addFlashAttribute("errorMessage", "Зайдите в систему");
           return "redirect:login";
        }
    }


    @PostMapping("sportsman_cabinet/event/{eventId}/edit")
    public String editScheduleElement( @Valid SportsmanEventCreateDTO sportsmanEventCreateDTO, @PathVariable Long eventId, RedirectAttributes redirectAttributes, BindingResult result) {
        String pathRedirect = String.format("redirect:/sportsman_cabinet/event/"+eventId);
        sportsmanEventsService.editEvent(sportsmanEventCreateDTO,eventId);
        redirectAttributes.addFlashAttribute("message", "Отредактрировано");
        return pathRedirect;
    }

    @GetMapping("sportsman_cabinet/event/{id}")
    public String getEventPage(Model model,@PathVariable String id,HttpServletRequest request){
        SportsmanEventsDTO sportsmanEventsDTO = sportsmanEventsService.getEventById(Long.parseLong(id));
        UserDTO userDTO = UserDTO.from(userService.getUserByUsername(request.getRemoteUser()));
        model.addAttribute("sportsman",userDTO);
        model.addAttribute("event",sportsmanEventsDTO);
        return "sportsman_event";
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("sportsman_cabinet/event/{eventId}/delete")
    public String deleteScheduleElement(@PathVariable Long eventId, RedirectAttributes redirectAttributes) {
        String pathRedirect = String.format("redirect:/sportsman_cabinet/");
        SportsmanEventsDTO event = sportsmanEventsService.deleteEventById(eventId);
        String message = String.format("Удалена заметка для %s на %s", event.getSportsman().getName(), event.getEventDate());
        redirectAttributes.addFlashAttribute("message", message);
        return pathRedirect;
    }

    @PreAuthorize("hasAuthority('CLIENT')")

    @GetMapping("sportsman_cabinet/event/{eventId}/deleteConnected")
    public String deleteScheduleElements(@PathVariable Long eventId, RedirectAttributes redirectAttributes) {
        String pathRedirect = String.format("redirect:/sportsman_cabinet/");
        SportsmanEventsDTO event = sportsmanEventsService.deleteEventsInSeries(eventId);
        String message = String.format("Удалены заметки для %s с %s", event.getSportsman().getName(), event.getEventDate());
        redirectAttributes.addFlashAttribute("message", message);
        return pathRedirect;
    }

    @PostMapping("/sportsman_cabinet/event/{eventId}/trainingProgram")
    public String addTrainingProgram( @PathVariable Long eventId, @RequestParam String content, RedirectAttributes redirectAttributes){
        String message = sportsmanEventsService.addEventTrainingProgram(content,eventId);
        String pathRedirect = String.format("redirect:/sportsman_cabinet/event/"+eventId);
        redirectAttributes.addFlashAttribute("programMessage",message);
        return pathRedirect;

    }

    @PostMapping("/sportsman_cabinet/event/{eventId}/food")
    public String addFood(@PathVariable Long eventId, @RequestParam String contentFood,RedirectAttributes redirectAttributes){
        String message = sportsmanEventsService.addEventFood(contentFood,eventId);
        String pathRedirect = String.format("redirect:/sportsman_cabinet/event/"+eventId);
        redirectAttributes.addFlashAttribute("programMessageFood",message);
        return pathRedirect;

    }

    @GetMapping("/calendar_sportsman/{groupId}/calendar/{eventId}")
    public String getScheduleElement(@PathVariable String groupId, @PathVariable Long eventId, Model model) {

        GroupDTO groupDTO = groupService.getGroupById(Long.parseLong(groupId));
        model.addAttribute("group", groupDTO);
        model.addAttribute("event", sportsmanEventsService.getEventByID(eventId));

        return "show_event";
    }


}
