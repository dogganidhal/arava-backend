package com.arava.rest.controller;

import com.arava.indexer.manager.SearchIndexManager;
import com.arava.persistence.entity.*;
import com.arava.rest.dto.LatLng;
import com.arava.rest.dto.PoiDto;
import com.arava.rest.dto.request.SearchRequest;
import com.arava.rest.dto.response.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 22:06
 * All rights reserved.
 */

@Slf4j
@RestController
@RequestMapping("/api/search")
public class SearchController {

  @Autowired
  private SearchIndexManager searchIndexManager;

  @PostMapping
  public SearchResponse searchPois(@RequestBody SearchRequest request) {
    List<PoiDto> pois = searchIndexManager.searchPois(request.searchQuery())
            .parallelStream()
            .map(this::poiDto)
            .collect(Collectors.toList());
    return SearchResponse.builder()
            .count(pois.size())
            .pois(pois)
            .build();
  }

  private PoiDto poiDto(Poi entity) {
    return PoiDto.builder()
            .id(entity.getId())
            .featured(entity.getFeatured())
            .sponsored(entity.getSponsored())
            .island(entity.getIsland().name())
            .category(categoryDto(entity.getCategory()))
            .coordinate(LatLng.builder()
                    .latitude(entity.getCoordinate().getX())
                    .longitude(entity.getCoordinate().getY())
                    .build()
            )
            .comments(entity.getComments().stream()
                    .map(this::commentDto)
                    .collect(Collectors.toList())
            )
            .medias(entity.getMedias().parallelStream()
                    .map(this::mediaDto)
                    .collect(Collectors.toList())
            )
            .ratings(ratingsDto(entity.getRatings()))
            .build();
  }

  private PoiDto.Ratings ratingsDto(List<Rating> ratings) {
    return PoiDto.Ratings.builder()
            .averageScore(ratings.stream()
                    .reduce((lhs, rhs) -> Rating.builder()
                            .score((lhs.getScore() + rhs.getScore()) / 2)
                            .build()
                    )
                    .orElseGet(() -> Rating.builder()
                            .score(0.0)
                            .build()
                    )
                    .getScore()
            )
            .count(ratings.size())
            .build();
  }

  private PoiDto.Comment commentDto(Comment entity) {
    return PoiDto.Comment.builder()
            .id(entity.getId())
            .content(entity.getContent())
            .date(entity.getCreated())
            .author(commentAuthorDto(entity.getUser()))
            .build();
  }

  private PoiDto.CommentAuthor commentAuthorDto(User entity) {
    return PoiDto.CommentAuthor.builder()
            .id(entity.getId())
            .fullName(entity.getFirstName() + " " + entity.getLastName())
            .avatar(mediaDto(entity.getAvatar()))
            .build();
  }

  private PoiDto.Media mediaDto(Media entity) {
    return PoiDto.Media.builder()
            .id(entity.getId())
            .type(entity.getMediaType())
            .url(entity.getUrl())
            .build();
  }

  private PoiDto.Category categoryDto(PoiCategory entity) {
    return PoiDto.Category.builder()
            .id(entity.getSection().getId())
            .name(entity.getSection().getName())
            .icon(mediaDto(entity.getSection().getIcon()))
            .section(categorySectionDto(entity.getSection()))
            .build();
  }

  private PoiDto.Category categorySectionDto(PoiCategorySection entity) {
    return PoiDto.Category.builder()
            .id(entity.getId())
            .name(entity.getName())
            .icon(mediaDto(entity.getIcon()))
            .build();
  }

}
