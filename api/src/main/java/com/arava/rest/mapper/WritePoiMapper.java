package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.PoiDetails;
import com.arava.persistence.entity.PoiLocalizedDescription;
import com.arava.persistence.repository.IslandRepository;
import com.arava.persistence.repository.PoiCategoryRepository;
import com.arava.rest.dto.request.MediaWriteRequest;
import com.arava.rest.dto.request.PoiWriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 21:50
 * All rights reserved.
 */


@Component
public class WritePoiMapper implements Mapper<PoiWriteRequest, Poi> {

  @Autowired
  private IslandRepository islandRepository;

  @Autowired
  private PoiCategoryRepository poiCategoryRepository;

  @Autowired
  private Mapper<PoiWriteRequest.LocalizedDescription, PoiLocalizedDescription> localizedDescriptionMapper;

  @Autowired
  private Mapper<MediaWriteRequest, Media> mediaMapper;

  @Autowired
  private Mapper<PoiWriteRequest.Details, PoiDetails> detailsMapper;

  @Override
  public Poi map(PoiWriteRequest object) {
    return Poi.builder()
            .id(object.getId())
            .latitude(object.getLatitude())
            .longitude(object.getLongitude())
            .island(islandRepository.getOne(object.getIslandId()))
            .category(poiCategoryRepository.getOne(object.getCategoryId()))
            .sponsored(object.getSponsored())
            .thingsToDo(object.getThingsToDo())
            .details(detailsMapper.map(object.getDetails()))
            .localizedDescriptions(object.getDescriptions().stream()
                    .map(localizedDescriptionMapper::map)
                    .collect(Collectors.toList())
            )
            .medias(object.getMedias().stream()
                    .map(mediaMapper::map)
                    .collect(Collectors.toList())
            )
            .build();
  }

}
