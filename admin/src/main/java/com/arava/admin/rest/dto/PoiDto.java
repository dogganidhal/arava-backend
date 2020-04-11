package com.arava.admin.rest.dto;

import com.arava.server.dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:40
 * All rights reserved.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiDto {

  private String id;
  private List<LocalizedResourceDto> title;
  private List<LocalizedResourceDto> description;
  private PoiDetailsDto details;
  private PoiTheme theme;
  private LatLng coordinate;
  private IslandDto island;
  private Boolean sponsored;
  private Boolean featured;
  private Boolean activity;
  private Boolean premium;
  private Boolean draft;
  private MediaDto mainImage;
  private List<MediaDto> medias;
  private List<RatingDto> comments;
  private UserDto owner;
  private RatingsDto ratings;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PoiTheme {

    private String id;
    private List<LocalizedResourceDto> name;
    private MediaDto icon;
    private MediaDto marker;
    private MediaDto sponsoredMarker;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PoiTheme parent;
    private List<PoiTheme> subThemes;

  }

}
