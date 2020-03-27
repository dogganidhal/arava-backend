package com.arava.business.manager;

import com.arava.persistence.entity.User;
import com.arava.server.dto.request.RateCommentRequest;

/**
 * Created by Nidhal Dogga
 * Date : 17/03/2020 14:16
 * All rights reserved.
 */

public interface ContentManager {
  void addComment(String poiId, User user, RateCommentRequest request);
  void approveComment(String commentId);
  void declineComment(String commentId);
}
