package com.arava.admin.rest.manager.impl;

import com.arava.admin.rest.manager.PoiManager;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.merger.EntityMerger;
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

  @Autowired
  private EntityMerger<Poi> poiMerger;

  @Override
  public Poi getById(UserPrincipal userPrincipal, String id) {
    Poi poi = poiRepository
            .findById(id)
            .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable);

    assertAuthorized(userPrincipal, poi);

    return poi;
  }

  @Override
  public void toggleDraft(UserPrincipal userPrincipal, String id) {
    Poi poi = poiRepository
            .findById(id)
            .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable);

    assertAuthorized(userPrincipal, poi);

    poi.setDraft(!poi.getDraft());
    poiRepository.save(poi);
  }

  @Override
  public List<Poi> listPois(UserPrincipal userPrincipal) {
    if (userPrincipal.isAdmin()) {
      return poiRepository.findAll();
    }
    return poiRepository.findByOwnerId(userPrincipal.getId());
  }

  @Override
  public void editPoi(UserPrincipal userPrincipal, PoiWriteRequest request) {
    Poi existingPoi = request.getId() != null ?
            poiRepository
                    .findById(request.getId())
                    .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable) :
            null;
    boolean operationAuthorized = userPrincipal.isAdmin() || (
            request.getFeatured() == null &&
            request.getSponsored() == null &&
            request.getOwnerId() == null &&
            request.getIslandId() == null &&
            request.getThemeId() == null &&
            existingPoi != null &&
            existingPoi.getOwner() != null &&
            existingPoi.getOwner().getId().equals(userPrincipal.getId())
    );

    if (!operationAuthorized) {
      throw ApiClientException.UNAUTHORIZED
              .getThrowable();
    }

    Poi poi = poiMerger.merge(existingPoi, writePoiMapper.deepMap(request));
    poiRepository.save(poi);
  }

  @Override
  public void deletePoi(UserPrincipal userPrincipal, String id) {
    Poi poi = poiRepository
            .findById(id)
            .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable);

    assertAuthorized(userPrincipal, poi);

    poi.setDisabled(true);
    poiRepository.save(poi);
  }

  private void assertAuthorized(UserPrincipal userPrincipal, Poi poi) {
    boolean authorized = userPrincipal.isAdmin() ||
            (poi.getOwner() != null && poi.getOwner().getId().equals(userPrincipal.getId()));
    if (!authorized) {
      throw ApiClientException.UNAUTHORIZED
              .getThrowable();
    }
  }

}
