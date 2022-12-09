package com.hackathon.server.model.auth;


import com.hackathon.server.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 DTO used for logging in/getting da token
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;

    private UserDTO user;

}
