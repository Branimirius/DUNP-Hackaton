package com.hackathon.server.repository.user;

import com.hackathon.server.model.user.User;
import com.hackathon.server.model.user.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    List<UserGroup> findAllByUsers(User user);

}
