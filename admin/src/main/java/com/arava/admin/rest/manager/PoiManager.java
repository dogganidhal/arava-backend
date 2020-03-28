package com.arava.admin.rest.manager;

import com.arava.persistence.entity.Poi;
import com.arava.server.dto.request.PoiWriteRequest;
import com.arava.server.jwt.UserPrincipal;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 26/03/2020 18:17
 * All rights reserved.
 */

public interface PoiManager {
  Poi getById(UserPrincipal userPrincipal, String id);
  void toggleDraft(UserPrincipal userPrincipal, String id);
  List<Poi> listPois(UserPrincipal userPrincipal);
  void editPoi(UserPrincipal userPrincipal, PoiWriteRequest request);
  void deletePoi(UserPrincipal userPrincipal, String id);
}
