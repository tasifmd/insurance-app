package com.tasif.userservice.service;

import com.tasif.userservice.dto.UserDto;
import com.tasif.userservice.exception.UserAlreadyExistsException;
import com.tasif.userservice.exception.UserNotFoundException;
import com.tasif.userservice.model.User;
import com.tasif.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private String username;

    @Override
    public User createUser(UserDto userDto) {
        userRepository.findByName(userDto.getName()).ifPresent(user -> {
            log.error("User already exist with " + user.getName());
            throw new UserAlreadyExistsException("User already exist with " + user.getName());
        });
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        log.info("User -- > " + user);
        user = userRepository.save(user);
        log.info("User registered success {}", user.getName());
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        log.info("Get user by name {}", username);
        User user = userRepository.findByName(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist"));
        return user;
    }
}
