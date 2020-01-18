package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiType;
import com.arava.rest.dto.PoiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:11
 * All rights reserved.
 */


@Component
public class PoiTypeBiMapper implements Mapper<PoiType, PoiDto.Type>, ReverseMapper<PoiType, PoiDto.Type> {

  @Autowired
  private Mapper<Media, PoiDto.Media> mediaMapper;

  @Override
  public PoiDto.Type map(PoiType object) {
    return PoiDto.Type.builder()
            .id(object.getId())
            .name(object.getName())
            .icon(mediaMapper.map(object.getIcon()))
            .build();
  }

  @Override
  public PoiType reverseMap(PoiDto.Type object) {
    return null;
  }

}
