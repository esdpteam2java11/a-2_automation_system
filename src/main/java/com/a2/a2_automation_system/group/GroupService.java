package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.common.Role;
import com.a2.a2_automation_system.exception.GroupNotFoundException;
import com.a2.a2_automation_system.user.UserDTO;
import com.a2.a2_automation_system.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public void addGroup(GroupDTO dto) {
        Group group = Group.builder()
                .name(dto.getName())
                .trainer(dto.getTrainer())
                .build();
        groupRepository.save(group);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream().map(GroupDTO::from).collect(Collectors.toList());
    }

    public GroupDTO getGroupById(Long id) {
        return GroupDTO.from(groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Группа не найдена")));
    }


    public List<UserDTO> getTrainers() {
        return userRepository.findByRole(Role.EMPLOYEE).stream().map(UserDTO::from).collect(Collectors.toList());
    }

}
