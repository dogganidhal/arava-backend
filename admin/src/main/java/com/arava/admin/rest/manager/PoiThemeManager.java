package com.arava.admin.rest.manager;

import com.arava.persistence.entity.PoiTheme;
import com.arava.server.dto.request.PoiThemeWriteRequest;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 07/04/2020 03:01
 * All rights reserved.
 */

public interface PoiThemeManager {
  PoiTheme getById(String id);
  List<PoiTheme> listPoiThemes();
  void createPoiTheme(PoiThemeWriteRequest request);
  void editPoiTheme(PoiThemeWriteRequest request);
  void deletePoiTheme(String id);
}
