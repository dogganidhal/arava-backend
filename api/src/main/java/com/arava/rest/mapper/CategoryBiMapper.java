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
import java.util.Map;

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
  private ReverseMapper<Map<String, String>, List<LocalizedResource>> localizedResourceReverseMapper;

  @Override
  public PoiDto.Category map(PoiCategory object) {
    return PoiDto.Category.builder()
            .id(object.getId())
            .name(localizedResourceReverseMapper.reverseMap(object.getName()))
            .icon(mediaMapper.map(object.getIcon()))
            .type(typeMapper.map(object.getType()))
            .build();
  }

  @Override
  public PoiCategory reverseMap(PoiDto.Category object) {
    return null;
  }

}
