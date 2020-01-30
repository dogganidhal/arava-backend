package com.arava.rest.mapper;

import com.arava.persistence.entity.Favorite;
import com.arava.persistence.entity.Poi;
import com.arava.rest.dto.FavoriteDto;
import com.arava.rest.dto.PoiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 09:59
 * All rights reserved.
 */

@Component
public class FavoriteMapper implements Mapper<Favorite, FavoriteDto> {

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @Override
  public FavoriteDto deepMap(Favorite object) {
    return FavoriteDto.builder()
            .id(object.getId())
            .poi(poiMapper.deepMap(object.getPoi()))
            .build();
  }

}
