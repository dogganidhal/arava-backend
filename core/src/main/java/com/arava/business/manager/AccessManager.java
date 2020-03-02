package com.arava.business.manager;

import com.arava.persistence.entity.RefreshToken;
import com.arava.server.dto.request.UpdateProfileRequest;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 08:19
 * All rights reserved.
 */

public interface AccessManager {

  RefreshToken generateRefreshTokenById(String userId);
  void revokeRefreshToken(String userId, String refreshToken) throws IllegalAccessException;
  void updateUserProfile(String id, UpdateProfileRequest request);

}
