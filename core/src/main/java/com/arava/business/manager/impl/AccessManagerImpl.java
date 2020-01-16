package com.arava.business.manager.impl;

import com.arava.business.manager.AccessManager;
import com.arava.persistence.entity.RefreshToken;
import com.arava.persistence.entity.User;
import com.arava.persistence.repository.RefreshTokenRepository;
import com.arava.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 08:20
 * All rights reserved.
 */


@Component
public class AccessManagerImpl implements AccessManager {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Override
  public RefreshToken generateRefreshTokenById(String userId) {
    Optional<User> user = userRepository.findById(userId);
    if (user.isPresent()) {
      RefreshToken token = RefreshToken.builder()
              .user(user.get())
              .build();
      return refreshTokenRepository.save(token);
    }
    throw new RuntimeException(String.format("User not found '%s'", userId));
  }

  @Override
  public void revokeRefreshTokenById(String id) {
    Optional<RefreshToken> tokenOptional = refreshTokenRepository.findById(id);
    if (tokenOptional.isPresent()) {
      RefreshToken token = tokenOptional.get();
      token.setRevoked(true);
      refreshTokenRepository.save(token);
    }
  }

}
