package com.hackathon.server.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 DTO model for user register
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String repeatPassword;

}
