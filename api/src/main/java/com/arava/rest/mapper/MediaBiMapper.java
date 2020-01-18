package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.rest.dto.PoiDto;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:03
 * All rights reserved.
 */


@Component
public class MediaBiMapper implements Mapper<Media, PoiDto.Media>, ReverseMapper<Media, PoiDto.Media> {

  @Override
  public PoiDto.Media map(Media object) {
    return PoiDto.Media.builder()
            .id(object.getId())
            .type(object.getMediaType())
            .url(object.getUrl())
            .build();
  }

  @Override
  public Media reverseMap(PoiDto.Media object) {
    return null;
  }

}
