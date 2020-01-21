package com.arava.rest.controller;

import com.arava.persistence.entity.Island;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.PoiCategory;
import com.arava.persistence.repository.IslandRepository;
import com.arava.persistence.repository.PoiCategoryRepository;
import com.arava.persistence.repository.PoiRepository;
import com.arava.rest.annotation.Admin;
import com.arava.rest.dto.IslandDto;
import com.arava.rest.dto.PoiDto;
import com.arava.rest.dto.request.PoiCategoryWriteRequest;
import com.arava.rest.dto.request.PoiWriteRequest;
import com.arava.rest.exception.ApiClientException;
import com.arava.rest.mapper.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 20:49
 * All rights reserved.
 */


@RestController
@RequestMapping("/poi")
public class PoiController {

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private PoiCategoryRepository poiCategoryRepository;

  @Autowired
  private IslandRepository islandRepository;

  @Autowired
  private Mapper<PoiWriteRequest, Poi> writePoiMapper;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @Autowired
  private Mapper<PoiCategoryWriteRequest, PoiCategory> poiCategoryMapper;

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  //region Poi CRUD operations

  @SneakyThrows
  @GetMapping
  public List<PoiDto> getAllPois() {
    try {
      return poiRepository.findAll().stream()
              .map(poiMapper::map)
              .collect(Collectors.toList());
    } catch (EntityNotFoundException e) {
      throw ApiClientException.NOT_FOUND
              .getThrowable();
    }
  }

  @SneakyThrows
  @GetMapping("/{poiId}")
  public PoiDto getPoi(@PathVariable("poiId") String poiId) {
    try {
      return poiMapper.map(poiRepository.getOne(poiId));
    } catch (EntityNotFoundException e) {
      throw ApiClientException.NOT_FOUND
              .getThrowable();
    }
  }

  @Admin
  @PostMapping
  public void createPoi(@RequestBody PoiWriteRequest request) {
    poiRepository.save(writePoiMapper.map(request));
  }

  @Admin
  @PutMapping
  public void updatePoi(@RequestBody PoiWriteRequest request) {
    poiRepository.save(writePoiMapper.map(request));
  }

  @Admin
  @SneakyThrows
  @DeleteMapping("/{poiId}")
  public void deletePoi(@PathVariable("poiId") String poiId) {
    try {
      Poi poi = poiRepository.getOne(poiId);
      poi.setDisabled(true);
      poiRepository.save(poi);
    } catch (EntityNotFoundException e) {
      throw ApiClientException.NOT_FOUND
              .getThrowable();
    }
  }

  //endregion

  //region Poi category CRUD operations

  @Admin
  @PostMapping("/category")
  public void createPoiCategory(@RequestBody PoiCategoryWriteRequest request) {
    PoiCategory poiCategory = poiCategoryMapper.map(request);
    poiCategoryRepository.save(poiCategory);
  }

  @PutMapping("/category")
  public void updatePoiCategory(@RequestBody PoiCategoryWriteRequest request) {
    PoiCategory poiCategory = poiCategoryMapper.map(request);
    poiCategoryRepository.save(poiCategory);
  }

  //endregion

  //region Island inspection

  @GetMapping("/island")
  public List<IslandDto> getIslands() {
    return islandRepository.findAll().stream()
            .map(islandMapper::map)
            .collect(Collectors.toList());
  }

  //endregion

}
