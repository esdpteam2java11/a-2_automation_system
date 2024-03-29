package com.a2.a2_automation_system.user;

import com.a2.a2_automation_system.exception.UserAlreadyRegisteredException;
import com.a2.a2_automation_system.exception.UserNotFoundException;
import com.a2.a2_automation_system.group.Group;
import com.a2.a2_automation_system.group.GroupRepository;
import com.a2.a2_automation_system.parent.Kinship;
import com.a2.a2_automation_system.parent.Parent;
import com.a2.a2_automation_system.parent.ParentRepository;
import com.a2.a2_automation_system.relationship.Relationship;
import com.a2.a2_automation_system.relationship.RelationshipRepository;
import com.a2.a2_automation_system.sportsmanpayments.OperationType;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPayment;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentDTO;
import com.a2.a2_automation_system.sportsmanpayments.SportsmanPaymentRepository;
import com.a2.a2_automation_system.userparam.UserParam;
import com.a2.a2_automation_system.userparam.UserParamDTO;
import com.a2.a2_automation_system.userparam.UserParamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        if ((isActive != null && role != null && !role.equals("")) && !role.equals("all")) {
            return userRepository.findAllByIsActiveAndRole(pageable, isActive, Role.valueOf(role)).map(UserDTO::from);
        } else if (isActive != null) {
            return userRepository.findAllByIsActive(pageable, isActive).map(UserDTO::from);
        } else if (role != null && !role.equals("")) {
            return userRepository.findAllByRoleExceptAdmin(pageable, Role.valueOf(role)).map(UserDTO::from);
        } else {
            return userRepository.findAllExceptAdmin(pageable).map(UserDTO::from);
        }
    }

    public Page<UserDTO> getUserBySearch(Pageable pageable, String search) {
        return userRepository.findUserByNameOrSurnameOrPatronymic(pageable, search).map(UserDTO::from);
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::from);
    }

    public List<UserShortInfoDTO> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(UserShortInfoDTO::from).collect(Collectors.toList());
    }

    public List<UserShortInfoDTO> getAllUsersByRole(Role role) {
        List<User> users = userRepository.findAllByRole(role);
        return users.stream().map(UserShortInfoDTO::from).collect(Collectors.toList());
    }

    public boolean userLoginCheck(String login) {
        return userRepository.existsByLogin(login);
    }

    public UserDTO addTrainer(UserDTO userDTO) {
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
        return UserDTO.from(trainer);
    }

    public SportsmanDTO getSportsmanDetails(Long id) {
        User sportsman = userRepository.findById(id).orElseThrow();
        Optional<UserParam> userParam = userParamRepository.findUpToDateParamsByUser(id, new Date());
        SportsmanPayment sportsmanPayment = sportsmanPaymentRepository.findUpToDateAmount(id,
                OperationType.ACCRUED.toString()).orElseThrow();
        if (userParam.isPresent()) return SportsmanDTO.from(sportsman, userParam.get(), sportsmanPayment);
        else return SportsmanDTO.from(sportsman, new UserParam(), sportsmanPayment);
    }

    @Transactional
    public void createSportsman(UserParamDTO userParamDTO,
                                SportsmanPaymentDTO sportsmanPaymentDTO,
                                UserDTO userDTO,
                                List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
                                List<String> pPatronymics, List<String> pPhones,
                                List<String> pWhatsapps, List<String> pTelegrams) {

        Group group = groupRepository.findById(userDTO.getGroupId()).orElseThrow();
        User sportsman = User.builder()
                .surname(userDTO.getSurname())
                .name(userDTO.getName())
                .patronymic(userDTO.getPatronymic())
                .birthDate(userDTO.getBirthDate())
                .phone(userDTO.getPhone())
                .whatsapp(userDTO.getWhatsapp())
                .telegram(userDTO.getTelegram())
                .address(userDTO.getAddress())
                .school(userDTO.getSchool())
                .channels(userDTO.getChannels())
                .group(group)
                .role(userDTO.getRole())
                .login(userDTO.getLogin())
                .dateOfAdmission(userDTO.getDateOfAdmission())
                .password(encoder.encode(userDTO.getPassword()))
                .build();

        userRepository.save(sportsman);

        sportsmanPaymentRepository.save(SportsmanPayment.builder()
                .amount(sportsmanPaymentDTO.getAmount())
                .date(sportsmanPaymentDTO.getDate())
                .user(sportsman)
                .operationType(OperationType.ACCRUED)
                .build());

        Date date = getCurrentDateWithStartTime();

        userParamRepository.save(UserParam.builder()
                .height(userParamDTO.getHeight())
                .weight(userParamDTO.getWeight())
                .creationDate(date)
                .user(sportsman)
                .build());

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

    @Transactional
    public void editSportsman(UserDTO userDTO, Long id,
                              UserParamDTO userParamDTO,
                              SportsmanPaymentDTO sportsmanPaymentDTO,
                              List<Long> pIds, List<String> pKinships, List<String> pSurnames, List<String> pNames,
                              List<String> pPatronymics, List<String> pPhones,
                              List<String> pWhatsapps, List<String> pTelegrams) {

        User sportsman = userRepository.findById(id).get();
        sportsman.setSurname(userDTO.getSurname());
        sportsman.setName(userDTO.getName());
        sportsman.setPatronymic(userDTO.getPatronymic() == null || userDTO.getPatronymic().isBlank() ? null : userDTO.getPatronymic());
        sportsman.setBirthDate(userDTO.getBirthDate());
        sportsman.setPhone(userDTO.getPhone());
        sportsman.setWhatsapp(userDTO.getWhatsapp() == null || userDTO.getWhatsapp().isBlank() ? null : userDTO.getWhatsapp());
        sportsman.setTelegram(userDTO.getTelegram() == null || userDTO.getTelegram().isBlank() ? null : userDTO.getTelegram());
        sportsman.setAddress(userDTO.getAddress());
        sportsman.setSchool(userDTO.getSchool() == null || userDTO.getSchool().isBlank() ? null : userDTO.getSchool());
        sportsman.setChannels(userDTO.getChannels() == null || userDTO.getChannels().isBlank() ? null : userDTO.getChannels());
        sportsman.setGroup(groupRepository.findById(userDTO.getGroupId()).get());
        sportsman.setDateOfAdmission(userDTO.getDateOfAdmission());
        sportsman.setLogin(userDTO.getLogin() == null || userDTO.getLogin().isBlank() ? null : userDTO.getLogin());

        if (!sportsman.isPasswordTheSame(userDTO.getPassword())) {
            sportsman.setPassword(userDTO.getPassword() == null || userDTO.getPassword().isBlank() ? null :
                    encoder.encode(userDTO.getPassword()));
        }

        userRepository.save(sportsman);

        if (!sportsmanPaymentRepository.existsByUserAndAmountAndDateAndOperationType(
                sportsman, sportsmanPaymentDTO.getAmount(),
                sportsmanPaymentDTO.getDate(), OperationType.ACCRUED)) {
            sportsmanPaymentRepository.save(SportsmanPayment.builder()
                    .amount(sportsmanPaymentDTO.getAmount())
                    .date(sportsmanPaymentDTO.getDate())
                    .user(sportsman)
                    .operationType(OperationType.ACCRUED)
                    .build());
        }

        Date date = getCurrentDateWithStartTime();
        List<UserParam> userParams = userParamRepository.getEqualParam(sportsman, userParamDTO.getHeight(),
                userParamDTO.getWeight(), date);

        if (userParams.size() == 0) {
            UserParam sportsmanParam = new UserParam();
            sportsmanParam.setCreationDate(date);
            sportsmanParam.setUser(sportsman);
            sportsmanParam.setWeight(userParamDTO.getWeight());
            sportsmanParam.setHeight(userParamDTO.getHeight());
            userParamRepository.save(sportsmanParam);
        }

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

            List<Relationship> relationships = relationshipRepository.findRelationshipByStudentId(sportsman.getId());
            for (Relationship relationship : relationships) {
                int counter = 0;
                for (Parent parent : parents) {
                    if (parent.getId().equals(relationship.getParent().getId()))
                        counter++;
                }
                if (counter == 0) relationshipRepository.delete(relationship);
            }

        } else relationshipRepository.deleteAllByStudentId(sportsman.getId());
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
                    pPatronymics.get(i).trim());
            parent.setPhone(pPhones.get(i));
            parent.setWhatsapp(pWhatsapps.get(i) == null || pWhatsapps.get(i).trim().isBlank() ? null :
                    pWhatsapps.get(i).trim());
            parent.setTelegram(pTelegrams.get(i) == null || pTelegrams.get(i).trim().isBlank() ? null :
                    pTelegrams.get(i).trim());
            parentRepository.save(parent);
            parents.add(parent);
        }
        return parents;
    }

    public void changeIsActive(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
    }

    public UserDTO getTrainer(Long id) {
        return UserDTO.from(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Такой тренер не найден")));
    }

    public void editTrainer(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Такой тренер не найден"));
        user.setLogin(userDTO.getLogin());

        if (!user.isPasswordTheSame(userDTO.getPassword())) {
            user.setPassword(userDTO.getPassword() == null || userDTO.getPassword().isBlank() ? null :
                    encoder.encode(userDTO.getPassword()));
        }

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

    public User getUserByUsername(String username) {
        var user = userRepository.findByLogin(username);
        return user.get();
    }

    public List<UserParamDTO> getUserParams(Long id) {
        return userParamRepository.findByUserId(id).stream().map(UserParamDTO::from).collect(Collectors.toList());
    }

    public List<SportsmanPaymentDTO> getUserPayments(Long id) {
        return sportsmanPaymentRepository.findByUserId(id).stream().map(SportsmanPaymentDTO::from).collect(Collectors.toList());
    }

    public String getGroupNameByUser(Long userId) {
        Long groupId = userRepository.findById(userId).get().getGroup().getId();
        return groupRepository.findById(groupId).get().getName();
    }

    public String getUserFio(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getSurname() + " " + user.getName() + (user.getPatronymic() != null ?
                (" " + user.getPatronymic()) : "");
    }

    private Date getCurrentDateWithStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public boolean checkPassword(UserPasswordDTO userPasswordDTO) {
        if(userPasswordDTO.getNewPassword().equals(userPasswordDTO.getConfirmPassword())){
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changePassword(UserPasswordDTO userPasswordDTO,User user) {
        if(checkPassword(userPasswordDTO)){
            user.setPassword(encoder.encode(userPasswordDTO.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
