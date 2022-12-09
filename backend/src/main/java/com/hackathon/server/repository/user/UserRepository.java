package com.hackathon.server.repository.user;

import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.user.User;
import com.hackathon.server.repository.AbstractStatusEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractStatusEntityRepository<User, Long> {

    Optional<User> findByUsernameAndEntityStatus(String username, EntityStatus entityStatus);

    User findOneByUsernameAndEntityStatus(String username, EntityStatus entityStatus);

    Optional<User> findByEmailAndEntityStatus(String email, EntityStatus entityStatus);

}
