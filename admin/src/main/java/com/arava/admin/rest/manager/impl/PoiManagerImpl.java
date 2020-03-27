package com.arava.admin.rest.manager.impl;

import com.arava.admin.rest.manager.PoiManager;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.repository.PoiRepository;
import com.arava.server.dto.request.PoiWriteRequest;
import com.arava.server.exception.ApiClientException;
import com.arava.server.jwt.UserPrincipal;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 26/03/2020 18:17
 * All rights reserved.
 */

@Component
public class PoiManagerImpl implements PoiManager {

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private Mapper<PoiWriteRequest, Poi> writePoiMapper;

  @Override
  public List<Poi> listPois(UserPrincipal userPrincipal) {
    if (userPrincipal.isAdmin()) {
      return poiRepository.findAll();
    }
    return poiRepository.findByOwnerId(userPrincipal.getId());
  }

  @Override
  public void editPoi(UserPrincipal userPrincipal, PoiWriteRequest request) {
    Poi poi = request.getId() != null ?
            poiRepository
                    .findById(request.getId())
                    .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable) :
            null;
    boolean operationAuthorized = userPrincipal.isAdmin() || (
            request.getFeatured() == null &&
            request.getSponsored() == null &&
            request.getOwnerId() == null &&
            poi != null &&
            poi.getOwner() != null &&
            poi.getOwner().getId().equals(userPrincipal.getId())
    );

    if (!operationAuthorized) {
      throw ApiClientException.UNAUTHORIZED
              .getThrowable();
    }

    poiRepository.save(writePoiMapper.deepMap(request));
  }

}
