package com.arava.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:11
 * All rights reserved.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IslandUpdateRequest {

  @NotNull
  private String id;
  private String name;
  private MediaWriteRequest image;
  private ArchipelagoUpdateRequest archipelago;
  private Double latitude;
  private Double longitude;
  private Double zoom;

}
