package com.arava.admin.rest.controller;

import com.arava.persistence.entity.AppVersionConfiguration;
import com.arava.persistence.repository.AppVersionConfigurationRepository;
import com.arava.server.annotation.Admin;
import com.arava.server.dto.AppVersionConfigurationDto;
import com.arava.server.mapper.ReverseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
  private AppVersionConfigurationRepository appVersionConfigurationRepository;

  @Autowired
  private ReverseMapper<AppVersionConfiguration, AppVersionConfigurationDto> appVersionConfigurationReverseMapper;

  @Admin
  @PutMapping("/version")
  public void updateVersionConfiguration(@Valid @RequestBody AppVersionConfigurationDto request) {
    AppVersionConfiguration versionConfiguration = appVersionConfigurationReverseMapper.reverseMap(request);
    appVersionConfigurationRepository.save(versionConfiguration);
  }

}
