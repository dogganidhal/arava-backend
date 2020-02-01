package com.arava.rest.controller;

import com.arava.persistence.entity.AppVersionConfiguration;
import com.arava.persistence.entity.Archipelago;
import com.arava.persistence.entity.PoiType;
import com.arava.persistence.repository.AppVersionConfigurationRepository;
import com.arava.persistence.repository.ArchipelagoRepository;
import com.arava.persistence.repository.PoiTypeRepository;
import com.arava.rest.annotation.Admin;
import com.arava.rest.dto.AppConfigurationDto;
import com.arava.rest.dto.ArchipelagoDto;
import com.arava.rest.dto.PoiDto;
import com.arava.rest.mapper.Mapper;
import com.arava.rest.mapper.ReverseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
  private PoiTypeRepository poiTypeRepository;

  @Autowired
  private AppVersionConfigurationRepository appVersionConfigurationRepository;

  @Autowired
  private Mapper<Archipelago, ArchipelagoDto> archipelagoMapper;

  @Autowired
  private Mapper<PoiType, PoiDto.Type> poiTypeMapper;

  @Autowired
  private Mapper<AppVersionConfiguration, AppConfigurationDto.AppVersionConfiguration> appVersionConfigurationMapper;

  @Autowired
  private ReverseMapper<AppVersionConfiguration, AppConfigurationDto.AppVersionConfiguration> appVersionConfigurationReverseMapper;

  @GetMapping("/configuration")
  public AppConfigurationDto getAppConfiguration() {
    return AppConfigurationDto.builder()
            .versionConfiguration(appVersionConfigurationMapper.deepMap(
                    appVersionConfigurationRepository.getLatestRevision()
            ))
            .archipelagos(archipelagoRepository.findAll().stream()
                    .map(archipelagoMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .poiTypes(poiTypeRepository.findAll().stream()
                    .map(poiTypeMapper::deepMap)
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
