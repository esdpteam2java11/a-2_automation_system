package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.exception.UserAlreadyRegisteredException;
import com.a2.a2_automation_system.exception.UserNotFoundException;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.parent.Kinship;
import com.a2.a2_automation_system.parent.Parent;
import com.a2.a2_automation_system.parent.ParentRepository;
import com.a2.a2_automation_system.relationship.Relationship;
import com.a2.a2_automation_system.relationship.RelationshipRepository;
import com.a2.a2_automation_system.tariff.OperationType;
import com.a2.a2_automation_system.tariff.SportsmanPayment;
import com.a2.a2_automation_system.tariff.SportsmanPaymentRepository;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserParamRepository userParamRepository;
    private final GroupRepository groupRepository;
    private final ParentRepository parentRepository;
    private final RelationshipRepository relationshipRepository;
    private final SportsmanPaymentRepository sportsmanPaymentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Role getSelectedUserRole(Long id) {
        return userRepository.findById(id).get().getRole();
    }

    public Page<UserDTO> getUsersWithFilter(Pageable pageable, String role, Boolean isActive) {
        if ((isActive != null && role != null) && !role.equals("all")) {
            return userRepository.findAllByIsActiveAndRole(pageable, isActive, Role.valueOf(role)).map(UserDTO::from);
        } else if (isActive != null) {
            return userRepository.findAllByIsActive(pageable, isActive).map(UserDTO::from);
        } else if (role != null) {
            return userRepository.findAllByRole(pageable, Role.valueOf(role)).map(UserDTO::from);
        } else {
            return userRepository.findAll(pageable).map(UserDTO::from);
        }
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::from);
    }

    public List<UserShortInfoDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserShortInfoDTO::from).collect(Collectors.toList());
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
                .patronymic(userDTO.getPatronymic())
                .password(encoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole())
                .address(userDTO.getAddress())
                .phone(userDTO.getPhone())
                .birthDate(userDTO.getBirthDate())
                .build();
        userRepository.save(trainer);
    }

    public SportsmanDTO getSportsmanDetails(Long id) {
        User sportsman = userRepository.findById(id).get();
        Optional<UserParam> userParam = userParamRepository.findUpToDateParamsByUser(id, new Date());
        if (userParam.isPresent()) return SportsmanDTO.from(sportsman, userParam.get());
        else return SportsmanDTO.from(sportsman, new UserParam());
    }

    public void createSportsman(String surname, String name, String patronymic, Date birthDate,
                                Double growth, Double weight,
                                String phone, String whatsapp, String telegram, String address, String school,
                                String channels, Long groupId, Date dateOfAdmission, Double sum, Date date,
                                String login, String password,
                                List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
                                List<String> pPatronymics, List<String> pPhones,
                                List<String> pWhatsapps, List<String> pTelegrams) {

        User sportsman = setUserFields(surname, name, patronymic, birthDate, phone, whatsapp, telegram, address, school,
                channels, groupId, dateOfAdmission, login, password, new User());
        sportsman.setRole(Role.CLIENT);

        userRepository.save(sportsman);

        createSportsmanNewTariff(sum, date, sportsman);

        UserParam sportsmanParam = new UserParam();
        setUserParams(growth, weight, sportsmanParam, sportsman);
        userParamRepository.save(sportsmanParam);

        if (pIds != null) {
            List<Parent> parents = setParents(pIds, pKinships, pSurnames, pNames, pPatronymics, pPhones, pWhatsapps,
                    pTelegrams);
            for (Parent parent : parents) {
                Relationship newRelationship = new Relationship();
                newRelationship.setStudent(sportsman);
                newRelationship.setParent(parent);
                relationshipRepository.save(newRelationship);
            }
        }
    }

    public void editSportsman(Long id, String surname, String name, String patronymic, Date birthDate,
                              Double growth, Double weight,
                              String phone, String whatsapp, String telegram, String address, String school,
                              String channels, Long groupId, Date dateOfAdmission, String login, String password,
                              List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
                              List<String> pPatronymics, List<String> pPhones,
                              List<String> pWhatsapps, List<String> pTelegrams) {

        User sportsman = userRepository.findById(id).get();
        setUserFields(surname, name, patronymic, birthDate, phone, whatsapp, telegram, address,
                school, channels, groupId, dateOfAdmission, login, password, sportsman);

        userRepository.save(sportsman);

        UserParam sportsmanParam = new UserParam();
        setUserParams(growth, weight, sportsmanParam, sportsman);
        userParamRepository.save(sportsmanParam);

        if (pIds != null) {
            List<Parent> parents = setParents(pIds, pKinships, pSurnames, pNames, pPatronymics, pPhones, pWhatsapps,
                    pTelegrams);
            for (Parent parent : parents) {
                if (!relationshipRepository.existsByParentIdAndStudentId(parent.getId(), sportsman.getId())) {
                    Relationship newRelationship = new Relationship();
                    newRelationship.setStudent(sportsman);
                    newRelationship.setParent(parent);
                    relationshipRepository.save(newRelationship);
                }
            }
        }
    }

    private User setUserFields(String surname, String name, String patronymic, Date birthDate,
                               String phone, String whatsapp, String telegram, String address, String school,
                               String channels, Long groupId, Date dateOfAdmission, String login, String password,
                               User sportsman) {
        sportsman.setSurname(surname);
        sportsman.setName(name);
        sportsman.setPatronymic(patronymic == null || patronymic.isBlank() ? null : patronymic);
        sportsman.setBirthDate(birthDate);
        sportsman.setPhone(phone);
        sportsman.setWhatsapp(whatsapp == null || whatsapp.isBlank() ? null : whatsapp);
        sportsman.setTelegram(telegram == null || telegram.isBlank() ? null : telegram);
        sportsman.setAddress(address);
        sportsman.setSchool(school == null || school.isBlank() ? null : school);
        sportsman.setChannels(channels == null || channels.isBlank() ? null : channels);
        sportsman.setGroup(groupRepository.findById(groupId).get());
        sportsman.setDateOfAdmission(dateOfAdmission);
        sportsman.setLogin(login == null || login.isBlank() ? null : login);
        sportsman.setPassword(password == null || password.isBlank() ? null : encoder.encode(password));
        return sportsman;
    }

    private void setUserParams(Double growth, Double weight, UserParam userParam, User user) {
        userParam.setCreationDate(new Date());
        userParam.setUser(user);
        userParam.setWeight(weight);
        userParam.setHeight(growth);
    }

    private void createSportsmanNewTariff(Double sum, Date date, User user) {
        sportsmanPaymentRepository.save(SportsmanPayment.builder()
                .amount(sum)
                .date(date)
                .user(user)
                .operationType(OperationType.ACCRUED)
                .build());
    }

    private List<Parent> setParents(List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
                                    List<String> pPatronymics, List<String> pPhones,
                                    List<String> pWhatsapps, List<String> pTelegrams) {
        List<Parent> parents = new ArrayList<>();
        for (int i = 0; i < pIds.size(); i++) {
            Parent parent;
            if (pIds.get(i) != 0L) {
                parent = parentRepository.findById(pIds.get(i)).get();
            } else {
                parent = new Parent();
            }
            parent.setKinship(Kinship.valueOf(pKinships.get(i)));
            parent.setSurname(pSurnames.get(i));
            parent.setName(pNames.get(i));
            parent.setPatronymic(pPatronymics.get(i) == null || pPatronymics.get(i).trim().isBlank() ? null :
                    pPatronymics.get(i));
            parent.setPhone(pPhones.get(i));
            parent.setWhatsapp(pWhatsapps.get(i) == null || pWhatsapps.get(i).trim().isBlank() ? null : pWhatsapps.get(i));
            parent.setTelegram(pTelegrams.get(i) == null || pTelegrams.get(i).trim().isBlank() ? null : pTelegrams.get(i));
            parentRepository.save(parent);
            parents.add(parent);
        }
        return parents;
    }

    public void changeIsActive(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        if (user.getIsActive()) {
            user.setIsActive(false);
        } else {
            user.setIsActive(true);
        }
        userRepository.save(user);
    }

    public UserDTO getTrainer(Long id) {
        return UserDTO.from(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Такой тренер не найден")));
    }

    public void editTrainer(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Такой тренер не найден"));
        user.setLogin(userDTO.getLogin());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setBirthDate(userDTO.getBirthDate());
        userRepository.save(user);
    }

    public boolean checkLogin(String login, Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user.getLogin().equals(login)) {
            return false;
        } else {
            return userRepository.existsByLogin(login);
        }
    }
}
