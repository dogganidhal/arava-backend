package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiCategory;
import com.arava.persistence.repository.PoiTypeRepository;
import com.arava.rest.dto.request.MediaWriteRequest;
import com.arava.rest.dto.request.PoiCategoryWriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 08:30
 * All rights reserved.
 */

@Component
public class WritePoiCategoryMapper implements Mapper<PoiCategoryWriteRequest, PoiCategory> {

  @Autowired
  private PoiTypeRepository poiTypeRepository;

  @Autowired
  private Mapper<MediaWriteRequest, Media> mediaMapper;

  @Override
  public PoiCategory map(PoiCategoryWriteRequest object) {
    return PoiCategory.builder()
            .id(object.getId())
            .type(object.getTypeId() != null ?poiTypeRepository.getOne(object.getTypeId()) : null)
            .icon(mediaMapper.map(object.getIcon()))
            .name(object.getName())
            .build();
  }

}
