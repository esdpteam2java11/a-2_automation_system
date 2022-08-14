package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.exception.GroupNotFoundException;
import com.a2.a2_automation_system.sportsmanpayments.OperationType;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentRepository;
import com.a2.a2_automation_system.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            UserShortInfoDTO userDto = UserShortInfoDTO.from(u);
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
}
