package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.user.Role;
import com.a2.a2_automation_system.exception.GroupNotFoundException;
import com.a2.a2_automation_system.user.User;
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
                .sum(dto.getSum())
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

    public Group getGroupByIdReturnGroup(Long id){
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Группа не найдена"));
    }

    public void editGroupName(Long id, String name){
        var group = groupRepository.findById(id);

        group.ifPresent(g -> {
            g.setName(name);

            groupRepository.save(g);

        });




    }
    public void editGroupSum(Long id,  int sum ){
        var group = groupRepository.findById(id);
        group.ifPresent(g -> {
            g.setSum(sum);
            groupRepository.save(g);
        });




    }
    public void editGroupTrainer(Long id,  User trainer){
        var group = groupRepository.findById(id);
        group.ifPresent(g -> {
            g.setTrainer(trainer);
            groupRepository.save(g);

        });


    }
    public void editGroup(Long id,User trainer,String name,int sum){
        editGroupTrainer(id,trainer);
        editGroupName(id,name);
        editGroupSum(id,sum);
    }

}
