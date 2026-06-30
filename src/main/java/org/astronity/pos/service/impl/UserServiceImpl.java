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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
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
    public User saveUser(UserDto userDto)
    {
        Role role = this.findRoleByName("ROLE_USER");
        return this.save(userDto, role);

    }

    @Override
    public User saveAdmin(UserDto userDto)
    {
        Role role = this.findRoleByName("ROLE_ADMIN");
        return this.save(userDto, role);
    }

    @Override
    public Optional<User> findByEmail(String email)
    {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers()
    {
        return this.userRepository.findAll()
                .stream()
                .map(this::mapToUserDto)
                .toList();
    }

    private User save(UserDto userDto, Role role)
    {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // hashed password
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.getUserRoles().add(this.buildUserRole(user, role));
        return userRepository.save(user);
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
