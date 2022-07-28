package com.a2.a2_automation_system.sportmancabinet;

import com.a2.a2_automation_system.group.GroupDTO;
import com.a2.a2_automation_system.group.GroupService;
import com.a2.a2_automation_system.user.UserDTO;
import com.a2.a2_automation_system.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Controller
@RequiredArgsConstructor
public class SportsmanEventsController {
    private final UserService userService;
    private final GroupService groupService;

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

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(FORBIDDEN)
    private String handleForbidden(Model model){
        model.addAttribute("errorMessage","У вас нет доступа");
        return "login";
    }
}
