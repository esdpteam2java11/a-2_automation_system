package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.parent.ParentRepository;
import com.a2.a2_automation_system.relationship.RelationshipRepository;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentRepository;
import com.a2.a2_automation_system.userparam.UserParam;
import com.a2.a2_automation_system.userparam.UserParamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {User.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserParamRepository userParamRepository;

    @Mock
    private SportsmanPaymentRepository paymentRepository;
    @Mock
    private RelationshipRepository relationshipRepository;

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private ParentRepository parentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    private User trainer;

    @BeforeEach
    void setupBeforeEach() {
        trainer = User.builder()
                .id(1L)
                .name("trainer_name1")
                .surname("trainer_name1")
                .patronymic("trainer_name1")
                .password("trainer_login1")
                .login("trainer_name1")
                .phone("+9965553230")
                .role(Role.EMPLOYEE)
                .birthDate(new Date())
                .build();
    }


    @Test
    void getTrainer() {
        doReturn(Optional.of(trainer)).when(userRepository).findById(1L);
        UserDTO trainerService = userService.getTrainer(1L);
        Assertions.assertThat(trainerService.getId()).isEqualTo(1L);
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
        when(this.userRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(page);
        Page<UserDTO> apiPageResp = userService.getAllUsers(page.getPageable());
        assertEquals(2L, apiPageResp.getTotalElements());
        Mockito.verify(this.userRepository, Mockito.times(1))
                .findAll(Mockito.any(Pageable.class));
    }


    @Test
    public void tesGetUserParam() {


        User user = new User(1L, "studentName_1", "studentSurname_1", "studentPatronymic_1", new Date(), "33-33-33", null, null, "г. Бишкек, ул. Ахунбаева 26", null, null, Role.CLIENT, "student_1", "123", true, Group.builder().build(), new Date());
        List<UserParam> userParamList = List.of(new UserParam());

        when(userParamRepository.findByUserId(1L)).thenReturn(userParamList);


        Assertions.assertThat(userParamList);
    }


    @Test
    public void tesGetUserPayment() {

        User user = new User(1L, "studentName_1", "studentSurname_1", "studentPatronymic_1", new Date(), "33-33-33", null, null, "г. Бишкек, ул. Ахунбаева 26", null, null, Role.CLIENT, "student_1", "123", true, Group.builder().build(), new Date());
        List<SportsmanPayment> sportsmanPayments = List.of(new SportsmanPayment());

        when(paymentRepository.findByUserId(1L)).thenReturn(sportsmanPayments);


        Assertions.assertThat(sportsmanPayments);
    }

    @Test
    void getSelectedUserRole() {
        doReturn(Optional.of(trainer)).when(userRepository).findById(1L);
        Role selectedUserRole = userService.getSelectedUserRole(1L);
        assertEquals(trainer.getRole(), selectedUserRole);
    }

    @Test
    void addTrainer() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(trainer);
        UserDTO created = userService.addTrainer(UserDTO.from(trainer));
        Assertions.assertThat(trainer.getName()).isEqualTo(created.getName());
    }

    @Test
    void getUserByUsername() {
        doReturn(Optional.of(trainer)).when(userRepository).findByLogin(trainer.getLogin());
        User user = userService.getUserByUsername(trainer.getLogin());
        Assertions.assertThat(trainer.getLogin()).isEqualTo(user.getLogin());
    }

    @Test
    void loadUserByUsername() {
        doReturn(Optional.of(trainer)).when(userRepository).findByLogin(trainer.getLogin());
        User user = userService.getUserByUsername(trainer.getLogin());
        Assertions.assertThat(trainer.getLogin()).isEqualTo(user.getLogin());
    }
}
