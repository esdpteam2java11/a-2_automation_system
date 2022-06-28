package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.exception.UserAlreadyRegisteredException;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.parent.Kinship;
import com.a2.a2_automation_system.parent.Parent;
import com.a2.a2_automation_system.parent.ParentRepository;
import com.a2.a2_automation_system.relationship.Relationship;
import com.a2.a2_automation_system.relationship.RelationshipRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserParamRepository userParamRepository;
    private final GroupRepository groupRepository;
    private final ParentRepository parentRepository;
    private final RelationshipRepository relationshipRepository;

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


    public void createSportsman(String surname, String name, String patronymic, Date birthDate,
                                Double growth, Double weight,
                                String phone, String whatsapp, String telegram, String address, String school,
                                String channels, Long groupId, Date dateOfAdmission, String login, String password,
                                List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
                                List<String> pPatronymics, List<String> pPhones,
                                List<String> pWhatsapps, List<String> pTelegrams) {

        User sportsman = new User();
        sportsman.setRole(Role.CLIENT);
        setUserFields(surname, name, patronymic, birthDate, phone, whatsapp, telegram, address, school,
                channels, groupId, dateOfAdmission, login, password, sportsman);
        userRepository.save(sportsman);

        UserParam sportsmanParam = new UserParam();
        setUserParams(growth, weight, sportsmanParam, sportsman);
        userParamRepository.save(sportsmanParam);

        if (pIds != null) {
            List<Parent> parents = setParents(pIds, pKinships, pSurnames, pNames, pPatronymics, pPhones, pWhatsapps,
                    pTelegrams, sportsman);
            for (Parent parent : parents) {
                Relationship newRelationship = new Relationship();
                newRelationship.setStudent(sportsman);
                newRelationship.setParent(parent);
                relationshipRepository.save(newRelationship);
            }
        }
    }

    private void setUserFields(String surname, String name, String patronymic, Date birthDate,
                               String phone, String whatsapp, String telegram, String address, String school,
                               String channels, Long groupId, Date dateOfAdmission, String login, String password,
                               User user) {
        user.setSurname(surname);
        user.setName(name);
        user.setPatronymic(patronymic);
        user.setBirthDate(birthDate);
        user.setPhone(phone);
        user.setWhatsapp(whatsapp);
        user.setTelegram(telegram);
        user.setAddress(address);
        user.setSchool(school);
        user.setChannels(channels);
        user.setGroup(groupRepository.findById(groupId).get());
        user.setDateOfAdmission(dateOfAdmission);
        user.setLogin(login);
        user.setPassword(encoder.encode(password));
    }

    private void setUserParams(Double growth, Double weight, UserParam userParam, User user) {
        userParam.setCreationDate(new Date());
        userParam.setUser(user);
        userParam.setWeight(weight);
        userParam.setHeight(growth);
    }

    private List<Parent> setParents(List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
                                    List<String> pPatronymics, List<String> pPhones,
                                    List<String> pWhatsapps, List<String> pTelegrams, User sportsman) {
        List<Parent> parents = new ArrayList<>();
        for (int i = 0; i < pIds.size(); i++) {
            Parent parent;
            if (pIds.get(i) != null) {
                parent = parentRepository.findById(pIds.get(i)).get();
            } else {
                parent = new Parent();
            }
            parent.setKinship(Kinship.valueOf(pKinships.get(i)));
            parent.setSurname(pSurnames.get(i));
            parent.setName(pNames.get(i));
            parent.setPatronymic(pPatronymics.get(i));
            parent.setPhone(pPhones.get(i));
            parent.setWhatsapp(pWhatsapps.get(i));
            parent.setTelegram(pTelegrams.get(i));
            parentRepository.save(parent);
            parents.add(parent);
        }
        return parents;
    }
}
