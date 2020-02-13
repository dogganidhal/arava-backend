package com.arava.rest.controller;

import com.arava.persistence.entity.AppVersionConfiguration;
import com.arava.persistence.entity.Archipelago;
import com.arava.persistence.entity.PoiTheme;
import com.arava.persistence.repository.AppVersionConfigurationRepository;
import com.arava.persistence.repository.ArchipelagoRepository;
import com.arava.persistence.repository.PoiThemeRepository;
import com.arava.rest.dto.AppConfigurationDto;
import com.arava.rest.dto.PoiDto;
import com.arava.server.dto.AppVersionConfigurationDto;
import com.arava.server.dto.ArchipelagoDto;
import com.arava.server.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 07:25
 * All rights reserved.
 */

@Slf4j
@RestController
@RequestMapping("/app")
public class AppController {

  @Autowired
  private ArchipelagoRepository archipelagoRepository;

  @Autowired
  private PoiThemeRepository poiThemeRepository;

  @Autowired
  private AppVersionConfigurationRepository appVersionConfigurationRepository;

  @Autowired
  private Mapper<Archipelago, ArchipelagoDto> archipelagoMapper;

  @Autowired
  private Mapper<PoiTheme, PoiDto.PoiTheme> poiThemeMapper;

  @Autowired
  private Mapper<AppVersionConfiguration, AppVersionConfigurationDto> versionConfigurationMapper;

  @GetMapping("/configuration")
  public AppConfigurationDto getAppConfiguration() {
    return AppConfigurationDto.builder()
            .versionConfiguration(versionConfigurationMapper.deepMap(
                    appVersionConfigurationRepository.getLatestRevision()
            ))
            .archipelagos(archipelagoRepository.findAll().stream()
                    .map(archipelagoMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .themes(poiThemeRepository.findAll().stream()
                    .map(poiThemeMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .build();
  }

}
