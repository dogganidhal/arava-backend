package com.arava.rest.mapper;

import com.arava.persistence.entity.*;
import com.arava.rest.dto.LatLng;
import com.arava.rest.dto.MediaDto;
import com.arava.rest.dto.PoiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
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
  private Mapper<Comment, PoiDto.Comment> commentMapper;

  @Autowired
  private Mapper<PoiCategory, PoiDto.Category> categoryMapper;

  @Autowired
  private Mapper<List<Rating>, PoiDto.Ratings> ratingsMapper;

  @Autowired
  private Mapper<Map<String, String>, List<LocalizedResource>> localizedResourceMapper;

  @Autowired
  private ReverseMapper<Map<String, String>, List<LocalizedResource>> localizedResourceReverseMapper;

  @Override
  public PoiDto map(Poi object) {
      return PoiDto.builder()
            .id(object.getId())
            .title(localizedResourceReverseMapper.reverseMap(object.getTitle()))
            .description(localizedResourceReverseMapper.reverseMap(object.getDescription()))
            .featured(object.getFeatured())
            .sponsored(object.getSponsored())
            .thingsToDo(object.getThingsToDo())
            .island(object.getIsland().getName())
            .category(categoryMapper.map(object.getCategory()))
            .coordinate(LatLng.builder()
                    .latitude(object.getLatitude())
                    .longitude(object.getLongitude())
                    .build()
            )
            .comments(object.getComments().stream()
                    .map(commentMapper::map)
                    .collect(Collectors.toList())
            )
            .medias(object.getMedias().stream()
                    .map(mediaMapper::map)
                    .collect(Collectors.toList())
            )
            .ratings(ratingsMapper.map(object.getRatings()))
            .build();
  }

  @Override
  public Poi reverseMap(PoiDto object) {
    return null;
  }

}
