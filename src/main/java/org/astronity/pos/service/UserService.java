package org.astronity.pos.service;

import org.astronity.pos.dto.UserDto;
import org.astronity.pos.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(UserDto userDto);
    User saveAdmin(UserDto userDto);

    Optional<User> findByEmail(String email);
    List<UserDto> findAllUsers();
}
