package com.arava.persistence.merger;

import com.arava.persistence.entity.Island;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 30/01/2020 10:05
 * All rights reserved.
 */

@Component
public class IslandEntityMerger implements EntityMerger<Island> {

  @Override
  public Island merge(Island src, Island dest) {
    return Island.builder()
            .id(pick(src.getId(), dest.getId()))
            .name(pick(src.getName(), dest.getName()))
            .archipelago(pick(src.getArchipelago(), dest.getArchipelago()))
            .latitude(pick(src.getLatitude(), dest.getLatitude()))
            .longitude(pick(src.getLongitude(), dest.getLongitude()))
            .zoom(pick(src.getZoom(), dest.getZoom()))
            .image(pick(src.getImage(), dest.getImage()))
            .build();
  }

}
