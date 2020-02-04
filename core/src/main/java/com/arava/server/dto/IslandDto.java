package com.arava.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private ArchipelagoDto archipelago;
  private LatLng center;
  private Double zoom;
  private MediaDto image;

}
