package com.arava.rest.controller;

import com.arava.persistence.entity.Archipelago;
import com.arava.persistence.entity.Island;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.repository.ArchipelagoRepository;
import com.arava.persistence.repository.IslandRepository;
import com.arava.persistence.repository.PoiRepository;
import com.arava.rest.dto.PoiDto;
import com.arava.server.dto.ArchipelagoDto;
import com.arava.server.dto.IslandDto;
import com.arava.server.exception.ApiClientException;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:29
 * All rights reserved.
 */


@RestController
public class IslandController {

  @Autowired
  private IslandRepository islandRepository;

  @Autowired
  private ArchipelagoRepository archipelagoRepository;

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  @Autowired
  private Mapper<Archipelago, ArchipelagoDto> archipelagoMapper;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  //region Island inspection

  @GetMapping("/island")
  public List<IslandDto> getIslands() {
    return islandRepository.findAll().stream()
            .map(islandMapper::deepMap)
            .collect(Collectors.toList());
  }

  @GetMapping("/island/{islandId}")
  public IslandDto getIsland(@PathVariable("islandId") String islandId) {
    return islandMapper.deepMap(islandRepository
            .findById(islandId)
            .orElseThrow(ApiClientException.ISLAND_NOT_FOUND::getThrowable)
    );
  }

  @GetMapping("/island/{islandId}/sponsored")
  public List<PoiDto> getSponsoredInIsland(@PathVariable("islandId") String islandId) {
    return poiRepository.findSponsoredInIsland(islandId).stream()
            .map(poiMapper::deepMap)
            .collect(Collectors.toList());
  }

  @GetMapping("/island/{islandId}/activities")
  public List<PoiDto> getActivitiesInIsland(@PathVariable("islandId") String islandId) {
    return poiRepository.findActivitiesInIsland(islandId).stream()
            .map(poiMapper::deepMap)
            .collect(Collectors.toList());
  }

  //endregion

  // region Archipelago inspection

  @GetMapping("/archipelago")
  public List<ArchipelagoDto> getArchipelagos() {
    return archipelagoRepository.findAll().stream()
            .map(archipelagoMapper::deepMap)
            .collect(Collectors.toList());
  }

  // endregion

}
