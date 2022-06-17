package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.exception.UserNotFoundException;
import com.a2.a2_automation_system.users.User;
import com.a2.a2_automation_system.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public void addGroup(String login, GroupDTO dto) {
        User user = userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
        Group group = Group.builder()
                .name(dto.getName())
                .trainer(user)
                .build();
        groupRepository.save(group);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream().map(GroupDTO::from).collect(Collectors.toList());
    }

}
