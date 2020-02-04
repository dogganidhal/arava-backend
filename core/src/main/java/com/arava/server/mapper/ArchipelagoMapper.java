package com.arava.server.mapper;

import com.arava.persistence.entity.Archipelago;
import com.arava.persistence.entity.Island;
import com.arava.server.dto.ArchipelagoDto;
import com.arava.server.dto.IslandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:27
 * All rights reserved.
 */

@Component
public class ArchipelagoMapper implements Mapper<Archipelago, ArchipelagoDto> {

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  @Override
  public ArchipelagoDto deepMap(Archipelago object) {
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
  public ArchipelagoDto partialMap(Archipelago object) {
    return ArchipelagoDto.builder()
            .id(object.getId())
            .name(object.getName())
            .build();
  }

}
