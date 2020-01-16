package com.arava.rest.dto;

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
  private Category category;
  private LatLng coordinate;
  private String island;
  private boolean sponsored;
  private boolean featured;
  private List<Media> medias;
  private List<Comment> comments;
  private Ratings ratings;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Category {

    private String id;
    private String name;
    private Media icon;
    private Category section;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Media {

    private String id;
    private String url;
    private String type;

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
    private CommentAuthor author;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class CommentAuthor {

    private String id;
    private String fullName;
    private Media avatar;

  }

}
