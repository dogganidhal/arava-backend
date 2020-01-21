package com.arava.rest.mapper;

import com.arava.persistence.entity.Island;
import com.arava.rest.dto.IslandDto;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 09:52
 * All rights reserved.
 */


@Component
public class IslandBiMapper implements Mapper<Island, IslandDto>, ReverseMapper<Island, IslandDto> {

  @Override
  public IslandDto map(Island object) {
    return IslandDto.builder()
            .id(object.getId())
            .name(object.getName())
            .build();
  }

  @Override
  public Island reverseMap(IslandDto object) {
    return null;
  }

}
