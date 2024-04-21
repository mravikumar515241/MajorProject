package com.finalproject.hotelbookingsystem.service;

import com.finalproject.hotelbookingsystem.dto.UserDto;
import com.finalproject.hotelbookingsystem.entity.Roles;
import com.finalproject.hotelbookingsystem.entity.User;
import com.finalproject.hotelbookingsystem.repository.UserRepository;
import com.finalproject.hotelbookingsystem.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        logger.info("User Service implementation constructor is called");
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        logger.info("create user is called");
        User user = modelMapper.map(userDto, User.class);
        List<Roles> roles = Arrays.stream(userDto.getRoles()
                .split(",")).map(w->new Roles(0,w)).collect(Collectors.toList());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);
        logger.info(user + "");
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }
}
