package com.arava.admin.rest.mapper;

import com.arava.admin.rest.dto.RatingDto;
import com.arava.admin.rest.dto.LocalizedResourceDto;
import com.arava.admin.rest.dto.PoiDto;
import com.arava.admin.rest.dto.UserDto;
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
  private Mapper<LocalizedResource<?>, LocalizedResourceDto> localizedResourceMapper;

  @Autowired
  private Mapper<PoiTheme, PoiDto.PoiTheme> poiThemeMapper;

  @Autowired
  private Mapper<Rating, RatingDto> commentMapper;

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<List<Rating>, RatingsDto> ratingsMapper;

  @Autowired
  private Mapper<PoiDetails, PoiDetailsDto> poiDetailsMapper;

  @Autowired
  private Mapper<User, UserDto> userMapper;

  @Override
  public PoiDto deepMap(Poi object) {
    return PoiDto.builder()
            .id(object.getId())
            .title(object.getTitle().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .owner(object.getOwner() != null ?
                    userMapper.deepMap(object.getOwner()) :
                    null
            )
            .description(object.getDescription().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .details(poiDetailsMapper.deepMap(object.getDetails()))
            .theme(poiThemeMapper.deepMap(object.getTheme()))
            .mainImage(object.getMainImage() != null ?
                    mediaMapper.deepMap(object.getMainImage()) :
                    null
            )
            .comments(object.getRatings().stream()
                    .map(commentMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .coordinate(LatLng.builder()
                    .latitude(object.getLatitude())
                    .longitude(object.getLongitude())
                    .build()
            )
            .draft(object.isDraft())
            .featured(object.isFeatured())
            .island(islandMapper.deepMap(object.getIsland()))
            .medias(object.getMedias().stream()
                    .map(mediaMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .ratings(ratingsMapper.deepMap(object.getRatings()))
            .sponsored(object.isSponsored())
            .activity(object.isActivity())
            .premium(object.isPremium())
            .defaultPremium(object.isDefaultPremium())
            .build();
  }

  @Override
  public PoiDto partialMap(Poi object) {
    return PoiDto.builder()
            .id(object.getId())
            .title(object.getTitle().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .owner(object.getOwner() != null ?
                    userMapper.deepMap(object.getOwner()) :
                    null
            )
            .description(object.getDescription().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .details(poiDetailsMapper.deepMap(object.getDetails()))
            .theme(poiThemeMapper.deepMap(object.getTheme()))
            .mainImage(object.getMainImage() != null ?
                    mediaMapper.deepMap(object.getMainImage()) :
                    null
            )
            .coordinate(LatLng.builder()
                    .latitude(object.getLatitude())
                    .longitude(object.getLongitude())
                    .build()
            )
            .draft(object.isDraft())
            .featured(object.isFeatured())
            .island(islandMapper.deepMap(object.getIsland()))
            .medias(object.getMedias().stream()
                    .map(mediaMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .ratings(ratingsMapper.deepMap(object.getRatings()))
            .sponsored(object.isSponsored())
            .activity(object.isActivity())
            .premium(object.isPremium())
            .defaultPremium(object.isDefaultPremium())
            .build();
  }

}
