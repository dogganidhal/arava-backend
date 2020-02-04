package com.arava.server.mapper;

import com.arava.persistence.entity.Media;
import com.arava.server.dto.MediaDto;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:03
 * All rights reserved.
 */


@Component
public class MediaMapper implements Mapper<Media, MediaDto> {

  @Override
  public MediaDto deepMap(Media object) {
    return MediaDto.builder()
            .id(object.getId())
            .type(object.getMediaType())
            .url(object.getUrl())
            .build();
  }

}
