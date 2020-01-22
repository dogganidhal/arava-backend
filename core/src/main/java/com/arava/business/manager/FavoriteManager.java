package com.arava.business.manager;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 10:10
 * All rights reserved.
 */

public interface FavoriteManager {

  void createByUserIdAndPoiId(String userId, String poiId);
  void synchronizeUserFavorites(List<String> poiIds, String userId);

}
