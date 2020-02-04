package com.arava.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 08:05
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

  private String id;
  private String content;
  private LocalDateTime date;
  private CommentAuthorDto author;

}
