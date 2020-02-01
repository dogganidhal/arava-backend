package com.arava.rest.mapper;

import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiCategory;
import com.arava.persistence.entity.PoiType;
import com.arava.rest.dto.MediaDto;
import com.arava.rest.dto.PoiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:09
 * All rights reserved.
 */


@Component
public class CategoryBiMapper implements Mapper<PoiCategory, PoiDto.Category>, ReverseMapper<PoiCategory, PoiDto.Category> {

  @Autowired
  private Mapper<PoiType, PoiDto.Type> typeMapper;

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<List<LocalizedResource>, String> localizedResourceMapper;

  @Override
  public PoiDto.Category deepMap(PoiCategory object) {
    return PoiDto.Category.builder()
            .id(object.getId())
            .name(localizedResourceMapper.deepMap(object.getName()))
            .icon(mediaMapper.deepMap(object.getIcon()))
            .type(typeMapper.deepMap(object.getType()))
            .build();
  }

  @Override
  public PoiCategory reverseMap(PoiDto.Category object) {
    return null;
  }

}
