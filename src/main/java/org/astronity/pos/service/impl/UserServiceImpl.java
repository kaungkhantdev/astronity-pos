package org.astronity.pos.service.impl;

import org.astronity.pos.dto.UserDto;
import org.astronity.pos.exception.RoleNotFoundException;
import org.astronity.pos.exception.UserNotFoundException;
import org.astronity.pos.model.Role;
import org.astronity.pos.model.User;
import org.astronity.pos.model.UserRole;
import org.astronity.pos.repository.RoleRepository;
import org.astronity.pos.repository.UserRepository;
import org.astronity.pos.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    )
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void saveUser(UserDto userDto)
    {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // hashed password
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // assign role
        Role role = this.findRoleByName("ROLE_USER");
        user.getUserRoles().add(this.buildUserRole(user, role));

        userRepository.save(user);

    }

    @Override
    public User findByEmail(String email)
    {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User email - "+ email +" is not found."));
    }

    @Override
    public List<UserDto> findAllUsers()
    {
        return this.userRepository.findAll()
                .stream()
                .map(this::mapToUserDto)
                .toList();
    }

    private Role findRoleByName(String name)
    {
        return this.roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role " + name + " is not found."));
    }

    private UserRole buildUserRole(User user, Role role)
    {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setAssignedAt(LocalDateTime.now());
        return userRole;
    }

    private UserDto mapToUserDto(User user)
    {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
