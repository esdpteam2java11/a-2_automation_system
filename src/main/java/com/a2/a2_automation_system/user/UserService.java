package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.common.Role;
import com.a2.a2_automation_system.exception.UserAlreadyRegisteredException;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.userparam.UserParam;
import com.a2.a2_automation_system.userparam.UserParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserParamRepository userParamRepository;
    private final GroupRepository groupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public Page<UserDTO> getUsersWithFilter(Pageable pageable, String role, Boolean isActive) {
        if ((isActive != null && role != null) && !role.equals("all")) {
            return userRepository.findAllByIsActiveAndRole(pageable, isActive, Role.getRoleByRoleName(role)).map(UserDTO::from);
        } else if (isActive != null) {
            return userRepository.findAllByIsActive(pageable, isActive).map(UserDTO::from);
        } else if (role != null) {
            return userRepository.findAllByRole(pageable, Role.getRoleByRoleName(role)).map(UserDTO::from);
        } else {
            return userRepository.findAll(pageable).map(UserDTO::from);
        }
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::from);
    }

    public Page<UserDTO> getUserBySearch(Pageable pageable, String search) {
        return userRepository.findUserByNameOrSurnameOrPatronymic(pageable, search).map(UserDTO::from);
    }

    public boolean userLoginCheck(String login) {
        return userRepository.existsByLogin(login);
    }

    public void addTrainer(UserDTO userDTO) {
        if (userLoginCheck(userDTO.getLogin())) {
            throw new UserAlreadyRegisteredException();
        }
        User trainer = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .login(userDTO.getLogin())
                .password(encoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole())
                .address(userDTO.getAddress())
                .phone(userDTO.getPhone())
                .birthDate(userDTO.getBirthDate())
                .build();
        userRepository.save(trainer);
    }


//    public void createSportsman(String surname, String name, String patronymic, Date birthDate,
//                                Double growth, Double weight,
//                                String phone, String whatsapp, String telegram, String address, String school,
//                                String channels, Long groupId, Date dateOfAdmission, String login, String password,
//                                List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
//                                List<String> pPatronymics, List<String> pPhones, List<String> pPhones1,
//                                List<String> pWhatsapps, List<String> pTelegrams) {
//        User sportsman = new User();
//        sportsman.setSurname(surname);
//        sportsman.setName(name);
//        sportsman.setSurname(patronymic);
//        sportsman.setBirthDate(birthDate);
//        sportsman.setPhone(phone);
//        sportsman.setWhatsapp(whatsapp);
//        sportsman.setTelegram(telegram);
//        sportsman.setAddress(address);
//        sportsman.setSchool(school);
//        sportsman.setChannels(channels);
//        sportsman.setGroup(groupRepository.findById(groupId).get());
//        sportsman.setDateOfAdmission(dateOfAdmission);
//    }
}
