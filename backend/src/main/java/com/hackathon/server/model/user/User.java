package com.hackathon.server.model.user;

import com.hackathon.server.model.user.enums.UserType;
import com.hackathon.server.model.AbstractStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
  model for user
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User extends AbstractStatusEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password_hash", length = 200)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @Column(length = 100)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private Boolean active;

    @ManyToMany
    @JoinTable(name = "user_user_group",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_group_id")})
    private List<UserGroup> userGroups = new ArrayList<>();

}
