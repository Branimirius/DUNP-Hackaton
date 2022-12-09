package com.hackathon.server.repository.user;

import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.user.UserPasswordResetToken;
import com.hackathon.server.repository.AbstractStatusEntityRepository;

import java.util.Optional;

public interface UserPasswordResetTokenRepository extends AbstractStatusEntityRepository<UserPasswordResetToken,Long> {

    Optional<UserPasswordResetToken> findByToken(String token);

}
