package com.arava.business.manager.impl;

import com.arava.business.manager.AccessManager;
import com.arava.persistence.entity.RefreshToken;
import com.arava.persistence.entity.User;
import com.arava.persistence.merger.EntityMerger;
import com.arava.persistence.repository.RefreshTokenRepository;
import com.arava.persistence.repository.UserRepository;
import com.arava.server.dto.request.UpdateProfileRequest;
import com.arava.server.exception.ApiClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
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

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EntityMerger<User> userMerger;

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
  public void revokeRefreshToken(String userId, String refreshToken) throws IllegalAccessException {
    Optional<RefreshToken> tokenOptional = refreshTokenRepository.findById(refreshToken);
    if (tokenOptional.isPresent()) {
      RefreshToken token = tokenOptional.get();
      if (!Objects.equals(token.getUser().getId(), userId)) {
        throw new IllegalAccessException();
      }
      token.setRevoked(true);
      refreshTokenRepository.save(token);
    }
  }

  @Override
  public void updateUserProfile(String id, UpdateProfileRequest request) {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    User oldUser = userRepository
            .findById(id)
            .get();
    boolean isPasswordCorrect = passwordEncoder
            .matches(request.getOldPassword(), oldUser.getPasswordHash());
    if (!isPasswordCorrect) {
      throw ApiClientException.BAD_CREDENTIALS
              .getThrowable();
    }
    if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) {
      throw ApiClientException.USER_EXISTS
              .getThrowable();
    }
    User userDiff = User.builder()
            .passwordHash(request.getPassword() != null ?
                    passwordEncoder.encode(request.getPassword()) :
                    null
            )
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build();
    User newUser = userMerger.merge(oldUser, userDiff);
    userRepository.save(newUser);
  }

}
