package com.arava.admin.rest.controller;

import com.arava.admin.rest.dto.PoiDto;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.PoiCategory;
import com.arava.persistence.entity.PoiType;
import com.arava.persistence.repository.PoiCategoryRepository;
import com.arava.persistence.repository.PoiRepository;
import com.arava.persistence.repository.PoiTypeRepository;
import com.arava.server.annotation.Admin;
import com.arava.server.dto.request.PoiCategoryWriteRequest;
import com.arava.server.dto.request.PoiTypeWriteRequest;
import com.arava.server.dto.request.PoiWriteRequest;
import com.arava.server.exception.ApiClientException;
import com.arava.server.mapper.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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
  private PoiTypeRepository poiTypeRepository;

  @Autowired
  private Mapper<PoiWriteRequest, Poi> writePoiMapper;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @Autowired
  private Mapper<PoiCategoryWriteRequest, PoiCategory> poiCategoryWriteMapper;

  @Autowired
  private Mapper<PoiCategory, PoiDto.PoiCategory> poiCategoryMapper;

  @Autowired
  private Mapper<PoiTypeWriteRequest, PoiType> poiTypeMapper;

  //region Poi CRUD operations

  @Admin
  @SneakyThrows
  @GetMapping
  public List<PoiDto> getAllPois() {
    try {
      return poiRepository.findAll().stream()
              .map(poiMapper::deepMap)
              .collect(Collectors.toList());
    } catch (EntityNotFoundException e) {
      throw ApiClientException.NOT_FOUND
              .getThrowable();
    }
  }

  @Admin
  @PostMapping
  public void createPoi(@Valid @RequestBody PoiWriteRequest request) {
    poiRepository.save(writePoiMapper.deepMap(request));
  }

  @Admin
  @PutMapping
  public void updatePoi(@Valid @RequestBody PoiWriteRequest request) {
    poiRepository.save(writePoiMapper.deepMap(request));
  }

  @Admin
  @SneakyThrows
  @DeleteMapping("/{poiId}")
  public void deletePoi(@PathVariable("poiId") String poiId) {
    Poi poi = poiRepository
            .findById(poiId)
            .orElseThrow(ApiClientException.NOT_FOUND::getThrowable);
    poi.setDisabled(true);
    poiRepository.save(poi);
  }

  //endregion

  //region Poi type / category CRUD operations

  @Admin
  @PostMapping("/category")
  public void createPoiCategory(@Valid @RequestBody PoiCategoryWriteRequest request) {
    PoiCategory poiCategory = poiCategoryWriteMapper.deepMap(request);
    poiCategoryRepository.save(poiCategory);
  }

  @Admin
  @PutMapping("/category")
  public void updatePoiCategory(@Valid @RequestBody PoiCategoryWriteRequest request) {
    PoiCategory poiCategory = poiCategoryWriteMapper.deepMap(request);
    poiCategoryRepository.save(poiCategory);
  }

  @Admin
  @GetMapping("/category")
  public List<PoiDto.PoiCategory> listPoiCategories() {
    return poiCategoryRepository.findAll().stream()
            .map(poiCategoryMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Admin
  @PostMapping("/type")
  public void createPoiType(@Valid @RequestBody PoiTypeWriteRequest request) {
    PoiType poiType = poiTypeMapper.deepMap(request);
    poiTypeRepository.save(poiType);
  }

  @Admin
  @PutMapping("/type")
  public void updatePoiType(@Valid @RequestBody PoiTypeWriteRequest request) {
    PoiType poiType = poiTypeMapper.deepMap(request);
    poiTypeRepository.save(poiType);
  }

  //endregion

}
