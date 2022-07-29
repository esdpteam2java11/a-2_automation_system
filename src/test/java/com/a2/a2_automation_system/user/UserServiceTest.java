package com.a2.a2_automation_system.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;


@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void getTrainer() {
        Long trainerId = 1L;
        User trainer = User.builder()
                .id(trainerId)
                .name("trainer_name1")
                .surname("trainer_name1")
                .patronymic("trainer_name1")
                .password("trainer_name1")
                .login("trainer_name1")
                .phone("+9965553230")
                .role(Role.EMPLOYEE)
                .birthDate(new Date())
                .build();
        doReturn(Optional.of(trainer)).when(userRepository).findById(trainerId);
        UserDTO trainerService = userService.getTrainer(trainerId);
        Assertions.assertThat(trainerId).isEqualTo(trainerService.getId());
        Assertions.assertThat(trainer.getName()).isEqualTo(trainerService.getName());
        Assertions.assertThat(trainer.getSurname()).isEqualTo(trainerService.getSurname());
    }


    @Test
    void getAllUsers() {
        User user = new User();
        user.setName("test");
        user.setSurname("testSurname");
        User user2 = new User();
        user2.setName("test_1");
        user2.setSurname("testSurname_1");
        List<User> apis = new ArrayList<>();
        apis.add(user);
        apis.add(user2);
        Page<User> page = new PageImpl<>(apis);
        Mockito.when(this.userRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(page);
        Page<UserDTO> apiPageResp  = userService.getAllUsers(page.getPageable());
        assertEquals(2L, apiPageResp.getTotalElements());
        Mockito.verify(this.userRepository, Mockito.times(1))
                .findAll(Mockito.any(Pageable.class));
    }
}