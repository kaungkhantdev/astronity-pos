package org.astronity.pos.config;

import org.astronity.pos.exception.RoleNotFoundException;
import org.astronity.pos.model.Role;
import org.astronity.pos.model.User;
import org.astronity.pos.model.UserRole;
import org.astronity.pos.repository.RoleRepository;
import org.astronity.pos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DataInitializer(
            RoleRepository roleRepository
    )
    {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        this.createRole("ROLE_USER");
        this.createRole("ROLE_ADMIN");
    }

    private void createRole(String name) {
        if (roleRepository.findByName(name).isEmpty()) {
            Role role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }
}
