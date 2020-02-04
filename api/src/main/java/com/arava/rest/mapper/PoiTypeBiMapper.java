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
 * Date : 18/01/2020 11:11
 * All rights reserved.
 */


@Component
public class PoiTypeBiMapper implements Mapper<com.arava.persistence.entity.PoiType, PoiDto.PoiType>, ReverseMapper<com.arava.persistence.entity.PoiType, PoiDto.PoiType> {

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<List<LocalizedResource>, String> localizedResourceMapper;

  @Override
  public PoiDto.PoiType deepMap(com.arava.persistence.entity.PoiType object) {
    return PoiDto.PoiType.builder()
            .id(object.getId())
            .name(localizedResourceMapper.deepMap(object.getName()))
            .icon(mediaMapper.deepMap(object.getIcon()))
            .build();
  }

  @Override
  public com.arava.persistence.entity.PoiType reverseMap(PoiDto.PoiType object) {
    return null;
  }

}
