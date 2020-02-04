package com.arava.rest.mapper;

import com.arava.persistence.entity.*;
import com.arava.rest.dto.PoiDto;
import com.arava.server.dto.CommentDto;
import com.arava.server.dto.LatLng;
import com.arava.server.dto.MediaDto;
import com.arava.server.dto.RatingsDto;
import com.arava.server.mapper.Mapper;
import com.arava.server.mapper.ReverseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 10:51
 * All rights reserved.
 */


@Component
public class PoiBiMapper implements Mapper<Poi, PoiDto>, ReverseMapper<Poi, PoiDto> {

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<Comment, CommentDto> commentMapper;

  @Autowired
  private Mapper<PoiCategory, PoiDto.PoiCategory> categoryMapper;

  @Autowired
  private Mapper<List<Rating>, RatingsDto> ratingsMapper;

  @Autowired
  private Mapper<List<LocalizedResource>, String> localizedResourceMapper;

  @Override
  public PoiDto deepMap(Poi object) {
      return PoiDto.builder()
            .id(object.getId())
            .title(localizedResourceMapper.deepMap(object.getTitle()))
            .description(localizedResourceMapper.deepMap(object.getDescription()))
            .featured(object.getFeatured())
            .sponsored(object.getSponsored())
            .thingsToDo(object.getThingsToDo())
            .island(object.getIsland().getName())
            .category(categoryMapper.deepMap(object.getCategory()))
            .coordinate(LatLng.builder()
                    .latitude(object.getLatitude())
                    .longitude(object.getLongitude())
                    .build()
            )
            .comments(object.getComments().stream()
                    .map(commentMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .medias(object.getMedias().stream()
                    .map(mediaMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .ratings(ratingsMapper.deepMap(object.getRatings()))
            .build();
  }

  @Override
  public Poi reverseMap(PoiDto object) {
    return null;
  }

}
