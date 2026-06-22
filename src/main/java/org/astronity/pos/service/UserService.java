package org.astronity.pos.service;

import org.astronity.pos.dto.UserDto;
import org.astronity.pos.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findByEmail(String email);
    List<UserDto> findAllUsers();
}
