package com.tasif.userservice.service;

import com.tasif.userservice.dto.UserDto;
import com.tasif.userservice.model.User;

public interface UserService {
    public User createUser(UserDto user);

    public User getUserByUsername(String username);
}
