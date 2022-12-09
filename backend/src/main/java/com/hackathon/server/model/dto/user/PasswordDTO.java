package com.hackathon.server.model.dto.user;

import lombok.Data;

/**
 DTO model for setting password
 */

@Data
public class PasswordDTO {

    private String token;

    private String newPassword;

}
