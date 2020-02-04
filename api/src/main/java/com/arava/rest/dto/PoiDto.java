package com.arava.rest.dto;

import com.arava.server.dto.CommentDto;
import com.arava.server.dto.LatLng;
import com.arava.server.dto.MediaDto;
import com.arava.server.dto.RatingsDto;
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
  private PoiCategory category;
  private LatLng coordinate;
  private String island;
  private Boolean sponsored;
  private Boolean featured;
  private Boolean thingsToDo;
  private List<MediaDto> medias;
  private List<CommentDto> comments;
  private RatingsDto ratings;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PoiType {

    private String id;
    private String name;
    private MediaDto icon;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PoiCategory {

    private String id;
    private String name;
    private MediaDto icon;
    @JsonInclude(Include.NON_NULL)
    private PoiType type;

  }

}
