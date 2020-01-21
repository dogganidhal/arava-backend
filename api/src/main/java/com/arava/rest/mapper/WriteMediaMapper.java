package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.rest.dto.request.MediaWriteRequest;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:30
 * All rights reserved.
 */


@Component
public class WriteMediaMapper implements Mapper<MediaWriteRequest, Media> {

  @Override
  public Media map(MediaWriteRequest object) {
    return Media.builder()
            .url(object.getUrl())
            .mediaType(object.getType())
            .build();
  }

}
