package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.rest.dto.MediaDto;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:03
 * All rights reserved.
 */


@Component
public class MediaBiMapper implements Mapper<Media, MediaDto>, ReverseMapper<Media, MediaDto> {

  @Override
  public MediaDto map(Media object) {
    return MediaDto.builder()
            .id(object.getId())
            .type(object.getMediaType())
            .url(object.getUrl())
            .build();
  }

  @Override
  public Media reverseMap(MediaDto object) {
    return null;
  }

}
