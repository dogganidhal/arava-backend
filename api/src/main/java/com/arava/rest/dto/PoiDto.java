package com.arava.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
  private Map<String, String> title;
  private Map<String, String> description;
  private Category category;
  private LatLng coordinate;
  private String island;
  private Boolean sponsored;
  private Boolean featured;
  private Boolean thingsToDo;
  private List<MediaDto> medias;
  private List<Comment> comments;
  private Ratings ratings;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Type {

    private String id;
    private Map<String, String> name;
    private MediaDto icon;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Category {

    private String id;
    private Map<String, String> name;
    private MediaDto icon;
    @JsonInclude(Include.NON_NULL)
    private Type type;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Ratings {

    private Integer count;
    private Double averageScore;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Comment {

    private String id;
    private String content;
    private LocalDate date;
    private User author;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class User {

    private String id;
    private String fullName;
    private MediaDto avatar;

  }

}
