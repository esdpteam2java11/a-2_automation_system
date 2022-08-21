package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.exception.GroupNotFoundException;
import com.a2.a2_automation_system.schedule.Schedule;
import com.a2.a2_automation_system.schedule.ScheduleService;
import com.a2.a2_automation_system.sportmancabinet.SportsmanEventsService;
import com.a2.a2_automation_system.sportsmanpayments.OperationType;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentRepository;
import com.a2.a2_automation_system.user.*;
import com.a2.a2_automation_system.visit.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final SportsmanPaymentRepository paymentRepository;
    private final VisitService visitService;
    private final ScheduleService scheduleService;

    public GroupDTO addGroup(GroupDTO dto) {
        Group group = Group.builder()
                .name(dto.getName())
                .trainer(dto.getTrainer())
                .sum(dto.getSum())
                .color(dto.getColor())
                .build();
        groupRepository.save(group);
        return GroupDTO.from(group);
    }

    public Boolean isColorExist(String color) {
        return groupRepository.existsByColor(color);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream().map(GroupDTO::from).collect(Collectors.toList());
    }

    public GroupDTO getGroupById(Long id) {
        return GroupDTO.from(groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Группа не найдена")));
    }

    public long getCountSportsmanInGroup(Long id) {
        return userRepository.findByGroup(id).stream()
                .filter(User::getIsActive)
                .count();
    }

    public List<UserDTO> getTrainers() {
        return userRepository.findByRole(Role.EMPLOYEE).stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public List<UserShortInfoDTO> getUsersByGroup(Long id) {
        List<UserShortInfoDTO> userShortInfoDTOList = new ArrayList<>();
        var users = userRepository.findByGroup(id);
        for (User u : users) {
            UserShortInfoDTO userDto;
            if(getAbsenceThreeDays(u.getLogin()))
            {
                userDto = UserShortInfoDTO.fromBackground(u);
            }
            else{
                userDto = UserShortInfoDTO.from(u);
            }

            Optional<SportsmanPayment> sportsmanPayment = paymentRepository.findUpToDateAmount(u.getId(), OperationType.ACCRUED.toString());
            if (sportsmanPayment.isPresent()) userDto.setAmount(sportsmanPayment.get().getAmount());
            else userDto.setAmount(0);
            userShortInfoDTOList.add(userDto);
        }
        return userShortInfoDTOList;
    }

    public Group getGroupByIdReturnGroup(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Группа не найдена"));
    }

    public void editGroup(Long id, GroupDTO groupDTO) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Группа не найдена"));
        group.setName(groupDTO.getName());
        group.setTrainer(groupDTO.getTrainer());
        group.setColor(groupDTO.getColor());
        group.setSum(groupDTO.getSum());
        groupRepository.save(group);
    }
    public Boolean getAbsenceThreeDays(String username){
        LocalDate now = LocalDate.now();
        var student = userRepository.findByLogin(username);
        LocalDate dateOfAdmission = LocalDate.ofInstant(student.get().getDateOfAdmission().toInstant(), ZoneId.systemDefault());
        Group group = student.get().getGroup();
        var visitListOptional = visitService.getLatestVisit(student.get());
        List<Schedule> eventsList = scheduleService.getListOfLastTreeEvents(group,now,dateOfAdmission);
        if(visitListOptional.get().size()>0){
            if (eventsList.size()>0){
                var schedule = eventsList.stream().filter(sch -> sch.equals(visitListOptional.get().get(0).getSchedule())).findFirst().orElse(null);
                if(schedule==null){
                        return true;
                    }
                }
        } else{
                if(eventsList.size()==3){
                    return true;
                }
        }
        return false;
    }

}
