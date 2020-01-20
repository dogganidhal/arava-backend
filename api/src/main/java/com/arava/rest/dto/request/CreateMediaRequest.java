package com.arava.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 21:38
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMediaRequest {

  private String url;
  private String type;

}
