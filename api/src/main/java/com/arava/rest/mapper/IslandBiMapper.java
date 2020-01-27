package com.arava.rest.mapper;

import com.arava.persistence.entity.Island;
import com.arava.persistence.entity.Media;
import com.arava.rest.dto.IslandDto;
import com.arava.rest.dto.LatLng;
import com.arava.rest.dto.MediaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 09:52
 * All rights reserved.
 */


@Component
public class IslandBiMapper implements Mapper<Island, IslandDto>, ReverseMapper<Island, IslandDto> {

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Override
  public IslandDto map(Island object) {
    return IslandDto.builder()
            .id(object.getId())
            .name(object.getName())
            .archipelago(object.getArchipelago().getName())
            .center(LatLng.builder()
                    .latitude(object.getLatitude())
                    .longitude(object.getLongitude())
                    .build()
            )
            .zoom(object.getZoom())
            .image(mediaMapper.map(object.getImage()))
            .build();
  }

  @Override
  public Island reverseMap(IslandDto object) {
    return null;
  }

}
