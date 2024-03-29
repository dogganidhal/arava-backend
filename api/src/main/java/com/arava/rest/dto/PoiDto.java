package com.arava.rest.dto;

import com.arava.server.dto.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 23:45
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiDto {

  private String id;
  private String title;
  private String description;
  private PoiDetailsDto details;
  private PoiTheme theme;
  private LatLng coordinate;
  private String island;
  private boolean light;
  private Boolean sponsored;
  private Boolean featured;
  private Boolean activity;
  private MediaDto mainImage;
  private List<MediaDto> medias;
  private List<CommentDto> comments;
  private RatingsDto ratings;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PoiTheme {

    private String id;
    private String name;
    private MediaDto icon;
    private MediaDto marker;
    private MediaDto sponsoredMarker;
    @JsonInclude(Include.NON_NULL)
    private PoiTheme parent;
    private List<PoiTheme> subThemes;

  }

}
