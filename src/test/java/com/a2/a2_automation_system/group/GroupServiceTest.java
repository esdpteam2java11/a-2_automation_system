package com.a2.a2_automation_system.group;

import com.a2.a2_automation_system.news.News;
import com.a2.a2_automation_system.user.Role;
import com.a2.a2_automation_system.user.User;
import com.a2.a2_automation_system.user.UserDTO;
import com.a2.a2_automation_system.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {Group.class})
@ExtendWith(SpringExtension.class)
class GroupServiceTest {

    @InjectMocks
    private GroupService groupService;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    private Group group;
    private final List<UserDTO> users = new ArrayList<>();

    @BeforeEach
    void setupBeforeEach() {
        group = Group.builder()
                .name("Группа 1")
                .trainer(new User())
                .sum(2000)
                .color("Красный")
                .build();
        users.add(UserDTO.builder()
                .name("userName")
                .surname("userSurname")
                .patronymic("userPatronymic")
                .birthDate(new Date())
                .phone("555555")
                .whatsapp("555555")
                .telegram("5555555")
                .address("address")
                .school("school")
                .role(Role.CLIENT)
                .login("userLogin")
                .password("password")
                .isActive(true)
                .groupId(group.getId())
                .dateOfAdmission(new Date())
                .build());
    }

    @Test
    void addGroup() {
        when(groupRepository.save(ArgumentMatchers.any(Group.class))).thenReturn(group);
        GroupDTO groupCreated = groupService.addGroup(GroupDTO.from(group));
        Assertions.assertThat(group.getName()).isEqualTo(groupCreated.getName());
        Assertions.assertThat(group.getTrainer()).isEqualTo(groupCreated.getTrainer());
        Assertions.assertThat(group.getSum()).isEqualTo(groupCreated.getSum());
        Assertions.assertThat(group.getColor()).isEqualTo(groupCreated.getColor());
    }


    @Test
    void getAllGroups() {
        List<GroupDTO> groups = groupService.getAllGroups();
        groups.add(new GroupDTO());
        groups.add(new GroupDTO());
        Assertions.assertThat(groups).hasSize(2).contains(groups.get(0), groups.get(1));
        verify(groupRepository).findAll();
    }

    @Test
    void isColorExist() {
        when(groupRepository.existsByColor(group.getColor())).thenReturn(true);
        Boolean colorExist = groupService.isColorExist(group.getColor());
        Assertions.assertThat(colorExist).isTrue();
    }

    @Test
    void getGroupById() {
        doReturn(Optional.of(group)).when(groupRepository).findById(group.getId());
        GroupDTO oneGroup = groupService.getGroupById(group.getId());
        Assertions.assertThat(group.getId()).isEqualTo(oneGroup.getId());
        Assertions.assertThat(group.getName()).isEqualTo(oneGroup.getName());
        Assertions.assertThat(group.getTrainer()).isEqualTo(oneGroup.getTrainer());
        Assertions.assertThat(group.getSum()).isEqualTo(oneGroup.getSum());
        Assertions.assertThat(group.getColor()).isEqualTo(oneGroup.getColor());
    }

    @Test
    void getCountSportsmanInGroup() {
        long count = users
                .stream()
                .filter(UserDTO::getIsActive)
                .count();
        Assertions.assertThat(count).isEqualTo(1);
    }

    @Test
    void getTrainers() {
        List<UserDTO> trainers = groupService.getTrainers();
        trainers.add(UserDTO.builder()
                .name("trainer_name1")
                .surname("trainer_name1")
                .patronymic("trainer_name1")
                .password("trainer_login1")
                .login("trainer_name1")
                .phone("+9965553230")
                .role(Role.EMPLOYEE)
                .birthDate(new Date())
                .build());
        trainers.add(UserDTO.builder()
                .name("trainer_name2")
                .surname("trainer_surname2")
                .patronymic("trainer_patronymic2")
                .password("trainer_password2")
                .login("trainer_login2")
                .phone("+9965553230")
                .role(Role.CLIENT)
                .birthDate(new Date())
                .build());
        Assertions.assertThat(trainers.stream().filter(c -> c.getRole().equals(Role.EMPLOYEE)).collect(Collectors.toList())).hasSize(1);
        verify(userRepository).findByRole(Role.EMPLOYEE);


    }


    @Test
    void getGroupByIdReturnGroup() {
        doReturn(Optional.of(group)).when(groupRepository).findById(group.getId());
        GroupDTO oneGroup = groupService.getGroupById(group.getId());
        Assertions.assertThat(group.getId()).isEqualTo(oneGroup.getId());
        Assertions.assertThat(group.getName()).isEqualTo(oneGroup.getName());
        Assertions.assertThat(group.getTrainer()).isEqualTo(oneGroup.getTrainer());
        Assertions.assertThat(group.getSum()).isEqualTo(oneGroup.getSum());
        Assertions.assertThat(group.getColor()).isEqualTo(oneGroup.getColor());
    }

    @Test
    void editGroup() {
        Group updatedGroup = Group.builder()
                .name("Группа 2")
                .trainer(new User())
                .sum(3000)
                .color("Зеленый")
                .build();
        given(groupRepository.findById(group.getId())).willReturn(Optional.of(group));
        groupService.editGroup(group.getId(), GroupDTO.from(updatedGroup));
        verify(groupRepository).save(updatedGroup);
        verify(groupRepository).findById(group.getId());
    }
}