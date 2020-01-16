package com.arava.business.manager;

import com.arava.persistence.entity.RefreshToken;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 08:19
 * All rights reserved.
 */

public interface AccessManager {

  RefreshToken generateRefreshTokenById(String userId);
  void revokeRefreshTokenById(String id);

}
