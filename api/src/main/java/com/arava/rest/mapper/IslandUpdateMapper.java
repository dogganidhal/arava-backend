package com.arava.rest.mapper;

import com.arava.persistence.entity.Island;
import com.arava.persistence.entity.Media;
import com.arava.persistence.repository.ArchipelagoRepository;
import com.arava.rest.dto.request.IslandUpdateRequest;
import com.arava.rest.dto.request.MediaWriteRequest;
import com.arava.rest.exception.ApiClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:15
 * All rights reserved.
 */

@Component
public class IslandUpdateMapper implements Mapper<IslandUpdateRequest, Island> {

  @Autowired
  private ArchipelagoRepository archipelagoRepository;

  @Autowired
  private Mapper<MediaWriteRequest, Media> mediaMapper;

  @Override
  public Island deepMap(IslandUpdateRequest object) {
    return Island.builder()
            .id(object.getId())
            .name(object.getName())
            .latitude(object.getLatitude())
            .longitude(object.getLongitude())
            .archipelago(object.getArchipelago() != null ?
                    archipelagoRepository
                            .findById(object.getArchipelago().getId())
                            .orElseThrow(ApiClientException.NOT_FOUND::getThrowable) :
                    null
            )
            .image(object.getImage() != null ?
                    mediaMapper.deepMap(object.getImage()) :
                    null
            )
            .zoom(object.getZoom())
            .build();
  }

}
