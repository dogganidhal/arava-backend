package com.arava.rest.mapper;

import com.arava.persistence.entity.AppVersionConfiguration;
import com.arava.rest.dto.AppConfigurationDto;
import org.springframework.stereotype.Component;



/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 07:49
 * All rights reserved.
 */

@Component
public class AppVersionConfigurationBiMapper
        implements Mapper<AppVersionConfiguration, AppConfigurationDto.AppVersionConfiguration>,
  ReverseMapper<AppVersionConfiguration, AppConfigurationDto.AppVersionConfiguration> {

  @Override
  public AppConfigurationDto.AppVersionConfiguration deepMap(AppVersionConfiguration object) {
    return AppConfigurationDto.AppVersionConfiguration.builder()
            .minVersion(object.getMinVersion())
            .force(object.getForceUpdate())
            .maxDate(object.getMaxDate())
            .build();
  }

  @Override
  public AppVersionConfiguration reverseMap(AppConfigurationDto.AppVersionConfiguration object) {
    return AppVersionConfiguration.builder()
            .minVersion(object.getMinVersion())
            .forceUpdate(object.getForce())
            .maxDate(object.getMaxDate())
            .build();
  }

}
