package com.arava.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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

  @NotBlank
  private String url;
  @NotBlank
  private String type;

}
