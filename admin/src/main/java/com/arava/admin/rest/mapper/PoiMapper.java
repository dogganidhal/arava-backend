package com.arava.admin.rest.mapper;

import com.arava.admin.rest.dto.LocalizedResourceDto;
import com.arava.admin.rest.dto.PoiDto;
import com.arava.persistence.entity.*;
import com.arava.server.dto.*;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:49
 * All rights reserved.
 */

@Component
public class PoiMapper implements Mapper<Poi, PoiDto> {

  @Autowired
  private Mapper<LocalizedResource, LocalizedResourceDto> localizedResourceMapper;

  @Autowired
  private Mapper<PoiCategory, PoiDto.PoiCategory> poiCategoryMapper;

  @Autowired
  private Mapper<Comment, CommentDto> commentMapper;

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<List<Rating>, RatingsDto> ratingsMapper;

  @Override
  public PoiDto deepMap(Poi object) {
    return PoiDto.builder()
            .id(object.getId())
            .title(object.getTitle().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .description(object.getDescription().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .category(poiCategoryMapper.deepMap(object.getCategory()))
            .comments(object.getComments().stream()
                    .map(commentMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .coordinate(new LatLng(object.getLatitude(), object.getLongitude()))
            .featured(object.getFeatured())
            .island(islandMapper.deepMap(object.getIsland()))
            .medias(object.getMedias().stream()
                    .map(mediaMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .ratings(ratingsMapper.deepMap(object.getRatings()))
            .sponsored(object.getSponsored())
            .thingsToDo(object.getThingsToDo())
            .build();
  }

}