package com.arava.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 21:38
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaWriteRequest {

  @NotNull
  private String url;

  @NotNull
  private String type;

}
