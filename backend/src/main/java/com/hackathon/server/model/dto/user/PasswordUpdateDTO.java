package com.hackathon.server.model.dto.user;

import lombok.Data;

/**
 DTO model for updating password
 */

@Data
public class PasswordUpdateDTO {

    private String currentPassword;

    private String newPassword;

    private String confirmPassword;

}
