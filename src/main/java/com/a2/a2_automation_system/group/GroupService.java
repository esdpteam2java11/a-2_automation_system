package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.commons.Role;

import com.a2.a2_automation_system.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repository;
    private final UserRepository userRepository;

    public void addGroup(String login,GroupDTO dto) {
        var user = userRepository.findByLogin(login);
        var group = Group.builder()
                .name(dto.getName())
                .trainer(user.get())
                .build();
        repository.save(group);
    }

}
