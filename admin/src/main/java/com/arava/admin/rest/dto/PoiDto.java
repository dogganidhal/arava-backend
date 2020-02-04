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
  private PoiCategory category;
  private LatLng coordinate;
  private IslandDto island;
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
    private List<LocalizedResourceDto> name;
    private MediaDto icon;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class PoiCategory {

    private String id;
    private List<LocalizedResourceDto> name;
    private MediaDto icon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PoiType type;

  }

}