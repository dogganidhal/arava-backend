package com.arava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 09:51
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IslandDto {

  private String id;
  private String name;

}
