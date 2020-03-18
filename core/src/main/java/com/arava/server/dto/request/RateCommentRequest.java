package com.arava.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nidhal Dogga
 * Date : 17/03/2020 14:21
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateCommentRequest {

  private String comment;
  private Double rating;

}
