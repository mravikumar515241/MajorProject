package com.finalproject.hotelbookingsystem.controller;

import com.finalproject.hotelbookingsystem.config.security.JwtUtility;
import com.finalproject.hotelbookingsystem.dto.LoginRequest;
import com.finalproject.hotelbookingsystem.dto.UserDto;
import com.finalproject.hotelbookingsystem.service.CustomUserDetailsService;
import com.finalproject.hotelbookingsystem.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("/user-api/v1")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final JwtUtility jwtUtility;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public UserController(JwtUtility jwtUtility, UserService userService, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtility = jwtUtility;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping(value = "/users")
    public UserDto createUser(@RequestBody @Valid UserDto userDto){
        logger.info("create user called ");
        return userService.createUser(userDto);
    }
    @PostMapping(value = "/users/login")
    public String bearerlogin(@RequestBody LoginRequest loginRequest) throws CredentialException {
        logger.info("bearer login called");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        if(!authentication.isAuthenticated()){
            throw new CredentialException("Invalid Credentials");
        }
        return jwtUtility.generateToken(loginRequest.getUsername());
    }

}