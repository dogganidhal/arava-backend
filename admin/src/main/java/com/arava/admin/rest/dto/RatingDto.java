package com.arava.admin.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 27/03/2020 19:10
 * All rights reserved.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

  private String id;
  private UserDto author;
  private PoiDto poi;
  private String comment;
  private LocalDateTime dateTime;
  private Status status;
  private Double score;

  public enum Status {
    APPROVED,
    DECLINED,
    UNKNOWN
  }

}
