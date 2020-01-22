package com.arava.rest.controller;

import com.arava.persistence.entity.AppVersionConfiguration;
import com.arava.persistence.entity.Island;
import com.arava.persistence.entity.PoiType;
import com.arava.persistence.repository.AppVersionConfigurationRepository;
import com.arava.persistence.repository.IslandRepository;
import com.arava.persistence.repository.PoiTypeRepository;
import com.arava.rest.annotation.Admin;
import com.arava.rest.dto.AppConfigurationDto;
import com.arava.rest.dto.IslandDto;
import com.arava.rest.dto.PoiDto;
import com.arava.rest.mapper.Mapper;
import com.arava.rest.mapper.ReverseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 07:25
 * All rights reserved.
 */

@RestController
@RequestMapping("/app")
public class AppController {

  @Autowired
  private IslandRepository islandRepository;

  @Autowired
  private PoiTypeRepository poiTypeRepository;

  @Autowired
  private AppVersionConfigurationRepository appVersionConfigurationRepository;

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  @Autowired
  private Mapper<PoiType, PoiDto.Type> poiTypeMapper;

  @Autowired
  private Mapper<AppVersionConfiguration, AppConfigurationDto.AppVersionConfiguration> appVersionConfigurationMapper;

  @Autowired
  private ReverseMapper<AppVersionConfiguration, AppConfigurationDto.AppVersionConfiguration> appVersionConfigurationReverseMapper;

  @GetMapping("/configuration")
  public AppConfigurationDto getAppConfiguration() {
    return AppConfigurationDto.builder()
            .versionConfiguration(appVersionConfigurationMapper.map(
                    appVersionConfigurationRepository.getLatestRevision()
            ))
            .islands(islandRepository.findAll().stream()
                    .map(islandMapper::map)
                    .collect(Collectors.toList())
            )
            .poiTypes(poiTypeRepository.findAll().stream()
                    .map(poiTypeMapper::map)
                    .collect(Collectors.toList())
            )
            .build();
  }

  @Admin
  @PutMapping("/version")
  public void updateVersionConfiguration(@Valid @RequestBody AppConfigurationDto.AppVersionConfiguration request) {
    appVersionConfigurationRepository.save(appVersionConfigurationReverseMapper.reverseMap(request));
  }

}
