package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.server.dto.request.MediaWriteRequest;
import com.arava.server.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:30
 * All rights reserved.
 */


@Component
public class WriteMediaMapper implements Mapper<MediaWriteRequest, Media> {

  @Override
  public Media deepMap(MediaWriteRequest object) {
    return Media.builder()
            .url(object.getUrl())
            .mediaType(object.getType())
            .build();
  }

}
