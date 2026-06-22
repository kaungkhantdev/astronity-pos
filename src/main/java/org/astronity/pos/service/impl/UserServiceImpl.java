package org.astronity.pos.service.impl;

import org.astronity.pos.dto.UserDto;
import org.astronity.pos.model.User;
import org.astronity.pos.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    
    @Override
    public void saveUser(UserDto userDto) {

    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return List.of();
    }
}
