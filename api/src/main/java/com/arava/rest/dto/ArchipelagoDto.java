package com.arava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:26
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArchipelagoDto {

  private String id;
  private String name;
  private List<IslandDto> islands;

}
