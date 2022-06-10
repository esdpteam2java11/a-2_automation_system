package com.a2.a2_automation_system.users;

import com.a2.a2_automation_system.commons.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public Page<UserDTO> listUser(Pageable pageable, String role, Boolean isActive) {
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

    public Page<UserDTO> getAllUsers(Pageable pageable){
        return  userRepository.findAll(pageable).map(UserDTO::from);
    }

}
