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


    public Page<UserDTO> listUser(Pageable pageable, Role role, boolean isActive) {
        if (isActive) {
            userRepository.findAllByIsActive(pageable, isActive).map(UserDTO::from);
        } else if (isActive) {
            userRepository.findAllByIsActive(pageable, isActive);
        } else if (!role.equals(null)) {
            userRepository.findAllByRole(pageable, role);
        } else if (isActive && !role.equals(null)) {
            userRepository.findAllByIsActiveAndRole(pageable, isActive, role);
        } else if (!isActive && !role.equals(null)) {
            userRepository.findAllByIsActiveAndRole(pageable, isActive, role);
        }
        return userRepository.findAll(pageable).map(UserDTO::from);
    }


}
