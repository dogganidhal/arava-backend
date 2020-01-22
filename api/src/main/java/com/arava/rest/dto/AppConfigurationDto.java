package com.arava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 07:28
 * All rights reserved.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppConfigurationDto {

  private AppVersionConfiguration versionConfiguration;
  private List<IslandDto> islands;
  private List<PoiDto.Type> poiTypes;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AppVersionConfiguration {

    private Integer minVersion;
    private LocalDate maxDate;
    private Boolean force;

  }

}
