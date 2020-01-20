package com.arava.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
  private List<LocalizedDescription> localizedDescription;
  private Category category;
  private LatLng coordinate;
  private String island;
  private boolean sponsored;
  private boolean featured;
  private List<MediaDto> medias;
  private List<Comment> comments;
  private Ratings ratings;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LocalizedDescription {

    private String title;
    private String description;
    private String language;
    private String languageCode;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Type {

    private String id;
    private String name;
    private MediaDto icon;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Category {

    private String id;
    private String name;
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
    private Date date;
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
