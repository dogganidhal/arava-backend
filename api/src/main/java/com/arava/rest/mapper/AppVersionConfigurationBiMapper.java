package com.arava.rest.mapper;

import com.arava.persistence.entity.AppVersionConfiguration;
import com.arava.server.dto.AppVersionConfigurationDto;
import com.arava.server.mapper.Mapper;
import com.arava.server.mapper.ReverseMapper;
import org.springframework.stereotype.Component;



/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 07:49
 * All rights reserved.
 */

@Component
public class AppVersionConfigurationBiMapper
        implements Mapper<AppVersionConfiguration, AppVersionConfigurationDto>,
        ReverseMapper<AppVersionConfiguration, AppVersionConfigurationDto> {

  @Override
  public AppVersionConfigurationDto deepMap(AppVersionConfiguration object) {
    return AppVersionConfigurationDto.builder()
            .minVersion(object.getMinVersion())
            .force(object.getForceUpdate())
            .maxDate(object.getMaxDate())
            .build();
  }

  @Override
  public AppVersionConfiguration reverseMap(AppVersionConfigurationDto object) {
    return AppVersionConfiguration.builder()
            .minVersion(object.getMinVersion())
            .forceUpdate(object.getForce())
            .maxDate(object.getMaxDate())
            .build();
  }

}
