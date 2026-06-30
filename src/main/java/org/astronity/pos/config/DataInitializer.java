package org.astronity.pos.config;

import org.astronity.pos.dto.UserDto;
import org.astronity.pos.exception.RoleNotFoundException;
import org.astronity.pos.model.Role;
import org.astronity.pos.model.User;
import org.astronity.pos.model.UserRole;
import org.astronity.pos.repository.RoleRepository;
import org.astronity.pos.repository.UserRepository;
import org.astronity.pos.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserService userService;

    public DataInitializer(
            RoleRepository roleRepository,
            UserService userService
    )
    {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        this.createRole("ROLE_USER");
        this.createRole("ROLE_ADMIN");
        this.createAdmin("admin@pos.com", "Abcd@1234");
    }

    private void createRole(String name) {
        if (roleRepository.findByName(name).isEmpty()) {
            Role role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }

    private void createAdmin(String email, String password)
    {
        if (this.userService.findByEmail(email).isEmpty())
        {
            UserDto userDto = new UserDto();
            userDto.setFirstName("admin");
            userDto.setLastName(email);
            userDto.setEmail(email);
            userDto.setPassword(password);

            User savedAdmin = this.userService.saveAdmin(userDto);
        }
    }
}
