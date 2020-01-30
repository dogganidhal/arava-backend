package com.arava.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
  private List<ArchipelagoDto> archipelagos;
  private List<PoiDto.Type> poiTypes;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class AppVersionConfiguration {

    private Integer minVersion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime maxDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean force;

  }

}
