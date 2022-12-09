package com.hackathon.server.model.dto.user;

import com.hackathon.server.model.user.UserGroup;
import com.hackathon.server.model.user.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 DTO model for user
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private UserType userType;

    private List<UserGroup> userGroups = new ArrayList<>();

    private String email;

    private String imageUrl;

//    private MultipartFile image;

}

//    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
//    private String password;
