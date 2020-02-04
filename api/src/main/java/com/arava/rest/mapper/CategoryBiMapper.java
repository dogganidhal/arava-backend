package com.arava.rest.mapper;

import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.entity.Media;
import com.arava.server.dto.MediaDto;
import com.arava.rest.dto.PoiDto;
import com.arava.server.mapper.Mapper;
import com.arava.server.mapper.ReverseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:09
 * All rights reserved.
 */


@Component
public class CategoryBiMapper implements Mapper<com.arava.persistence.entity.PoiCategory, PoiDto.PoiCategory>, ReverseMapper<com.arava.persistence.entity.PoiCategory, PoiDto.PoiCategory> {

  @Autowired
  private Mapper<com.arava.persistence.entity.PoiType, PoiDto.PoiType> typeMapper;

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<List<LocalizedResource>, String> localizedResourceMapper;

  @Override
  public PoiDto.PoiCategory deepMap(com.arava.persistence.entity.PoiCategory object) {
    return PoiDto.PoiCategory.builder()
            .id(object.getId())
            .name(localizedResourceMapper.deepMap(object.getName()))
            .icon(mediaMapper.deepMap(object.getIcon()))
            .type(typeMapper.deepMap(object.getType()))
            .build();
  }

  @Override
  public com.arava.persistence.entity.PoiCategory reverseMap(PoiDto.PoiCategory object) {
    return null;
  }

}
