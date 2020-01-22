package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiCategory;
import com.arava.persistence.entity.PoiType;
import com.arava.rest.dto.request.MediaWriteRequest;
import com.arava.rest.dto.request.PoiCategoryWriteRequest;
import com.arava.rest.dto.request.PoiTypeWriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 23:24
 * All rights reserved.
 */


@Component
public class WritePoiTypeMapper implements Mapper<PoiTypeWriteRequest, PoiType> {

  @Autowired
  private Mapper<PoiCategoryWriteRequest, PoiCategory> poiCategoryMapper;

  @Autowired
  private Mapper<MediaWriteRequest, Media> mediaMapper;

  @Override
  public PoiType map(PoiTypeWriteRequest object) {
    PoiType type = PoiType.builder()
            .id(object.getId())
            .icon(mediaMapper.map(object.getIcon()))
            .name(object.getName())
            .categories(object.getCategories().stream()
                    .map(poiCategoryMapper::map)
                    .collect(Collectors.toList())
            )
            .build();
    type.getCategories()
            .forEach(category -> category.setType(type));
    return type;
  }

}
