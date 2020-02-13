package com.arava.rest.dto;

import com.arava.server.dto.AppVersionConfigurationDto;
import com.arava.server.dto.ArchipelagoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  private AppVersionConfigurationDto versionConfiguration;
  private List<ArchipelagoDto> archipelagos;
  private List<PoiDto.PoiTheme> themes;

}
