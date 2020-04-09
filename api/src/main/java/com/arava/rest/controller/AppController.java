package com.arava.rest.controller;

import com.arava.persistence.entity.AppVersionConfiguration;
import com.arava.persistence.entity.Archipelago;
import com.arava.persistence.entity.PoiTheme;
import com.arava.persistence.repository.AppVersionConfigurationRepository;
import com.arava.persistence.repository.ArchipelagoRepository;
import com.arava.persistence.repository.IslandRepository;
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

import java.util.ArrayList;
import java.util.List;
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
  private IslandRepository islandRepository;

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
                    .map(archipelago -> Archipelago.builder()
                            .id(archipelago.getId())
                            .name(archipelago.getName())
                            .created(archipelago.getCreated())
                            .disabled(archipelago.isDisabled())
                            .updated(archipelago.getUpdated())
                            .islands(archipelago.getIslands().stream()
                                    .filter(island -> islandRepository.countActivePois(island.getId()) > 0)
                                    .collect(Collectors.toList())
                            )
                            .build()
                    )
                    .filter(archipelago -> !archipelago.getIslands().isEmpty())
                    .map(archipelagoMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .themes(poiThemeRepository.findAllHavingPois().stream()
                    .reduce(
                            new ArrayList<PoiTheme>(),
                            (accumulator, theme) -> {
                              ArrayList<PoiTheme> themeList = new ArrayList<>(accumulator);
                              appendThemeHierarchy(themeList, theme);
                              return themeList;
                            },
                            (rhs, lhs) -> {
                              ArrayList<PoiTheme> themeList = new ArrayList<>(lhs);
                              themeList.addAll(rhs);
                              return themeList;
                            }
                    )
                    .stream()
                    .map(poiThemeMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .build();
  }

  private void appendThemeHierarchy(List<PoiTheme> themes, PoiTheme theme) {
    if (themes.stream().noneMatch(t -> t.getId().equals(theme.getId()))) {
      themes.add(theme);
    }
    if (theme.getParent() != null) {
      appendThemeHierarchy(themes, theme.getParent());
    }
  }

}
