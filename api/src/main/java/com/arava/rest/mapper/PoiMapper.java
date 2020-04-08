package com.arava.rest.mapper;

import com.arava.persistence.entity.*;
import com.arava.rest.dto.CommentDto;
import com.arava.rest.dto.PoiDto;
import com.arava.server.dto.LatLng;
import com.arava.server.dto.MediaDto;
import com.arava.server.dto.PoiDetailsDto;
import com.arava.server.dto.RatingsDto;
import com.arava.server.mapper.Mapper;
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
public class PoiMapper implements Mapper<Poi, PoiDto> {

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<Comment, CommentDto> commentMapper;

  @Autowired
  private Mapper<PoiTheme, PoiDto.PoiTheme> themeMapper;

  @Autowired
  private Mapper<List<Rating>, RatingsDto> ratingsMapper;

  @Autowired
  private Mapper<List<LocalizedResource<Poi>>, String> localizedResourceMapper;

  @Autowired
  private Mapper<PoiDetails, PoiDetailsDto> poiDetailsMapper;

  @Override
  public PoiDto deepMap(Poi object) {
      return PoiDto.builder()
            .id(object.getId())
            .title(localizedResourceMapper.deepMap(object.getTitle()))
            .description(localizedResourceMapper.deepMap(object.getDescription()))
            .details(poiDetailsMapper.deepMap(object.getDetails()))
            .featured(object.isFeatured())
            .sponsored(object.isSponsored())
            .activity(object.isActivity())
            .island(object.getIsland().getName())
            .theme(themeMapper.deepMap(object.getTheme()))
            .mainImage(object.getMainImage() != null ?
                    mediaMapper.deepMap(object.getMainImage()) :
                    null
            )
            .coordinate(LatLng.builder()
                    .latitude(object.getLatitude())
                    .longitude(object.getLongitude())
                    .build()
            )
            .comments(object.getComments().stream()
                    .filter(comment -> CommentStatus.APPROVED.equals(comment.getStatus()))
                    .filter(comment -> !comment.getDisabled())
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

}
