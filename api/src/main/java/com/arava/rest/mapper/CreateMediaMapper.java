package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.rest.dto.request.CreateMediaRequest;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:30
 * All rights reserved.
 */


@Component
public class CreateMediaMapper implements Mapper<CreateMediaRequest, Media> {

  @Override
  public Media map(CreateMediaRequest object) {
    return Media.builder()
            .url(object.getUrl())
            .mediaType(object.getType())
            .build();
  }

}
