package com.hackathon.server.controller;

import com.hackathon.server.model.auth.AuthRequest;
import com.hackathon.server.model.auth.AuthResponse;
import com.hackathon.server.model.dto.user.UserDTO;
import com.hackathon.server.model.dto.user.UserRegisterDTO;
import com.hackathon.server.service.user.UserService;
import com.hackathon.server.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
    Controller used for generating the token and register user
 */
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorizationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        return userService.generateAuthResponse(authRequest.getUsername());
    }

    @PostMapping("/register")
    public UserDTO login(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.register(userRegisterDTO);
    }

}
