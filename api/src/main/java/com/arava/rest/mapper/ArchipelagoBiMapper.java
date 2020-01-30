package com.arava.rest.mapper;

import com.arava.persistence.entity.Archipelago;
import com.arava.persistence.entity.Island;
import com.arava.rest.dto.ArchipelagoDto;
import com.arava.rest.dto.IslandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:27
 * All rights reserved.
 */

@Component
public class ArchipelagoBiMapper implements Mapper<Archipelago, ArchipelagoDto>, ReverseMapper<Archipelago, ArchipelagoDto> {

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  @Override
  public ArchipelagoDto map(Archipelago object) {
    return ArchipelagoDto.builder()
            .id(object.getId())
            .name(object.getName())
            .islands(object.getIslands().stream()
                    .map(islandMapper::partialMap)
                    .collect(Collectors.toList())
            )
            .build();
  }

  @Override
  public Archipelago reverseMap(ArchipelagoDto object) {
    return null;
  }

}
