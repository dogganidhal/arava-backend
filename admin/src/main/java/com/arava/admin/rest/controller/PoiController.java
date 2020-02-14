package com.arava.admin.rest.controller;

import com.arava.admin.rest.dto.PoiDto;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.PoiTheme;
import com.arava.persistence.repository.PoiThemeRepository;
import com.arava.persistence.repository.PoiRepository;
import com.arava.server.annotation.Admin;
import com.arava.server.dto.request.PoiThemeWriteRequest;
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
  private PoiThemeRepository poiThemeRepository;

  @Autowired
  private Mapper<PoiWriteRequest, Poi> writePoiMapper;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @Autowired
  private Mapper<PoiThemeWriteRequest, PoiTheme> writePoiThemeMapper;

  @Autowired
  private Mapper<PoiTheme, PoiDto.PoiTheme> poiThemeMapper;

  //region Poi CRUD operations
  
  @Admin
  @GetMapping("/{poiId}")
  public PoiDto getPoi(@PathVariable("poiId") String poiId) {
    Poi poi = poiRepository
            .findById(poiId)
            .orElseThrow(ApiClientException.NOT_FOUND::getThrowable);
    return poiMapper.deepMap(poi);
  }

  @Admin
  @SneakyThrows
  @GetMapping
  public List<PoiDto> listPois() {
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
  @PostMapping("/theme")
  public void createPoiCategory(@Valid @RequestBody PoiThemeWriteRequest request) {
    PoiTheme poiCategory = writePoiThemeMapper.deepMap(request);
    poiThemeRepository.save(poiCategory);
  }

  @Admin
  @PutMapping("/theme")
  public void updatePoiCategory(@Valid @RequestBody PoiThemeWriteRequest request) {
    PoiTheme poiCategory = writePoiThemeMapper.deepMap(request);
    poiThemeRepository.save(poiCategory);
  }

  @Admin
  @GetMapping("/theme")
  public List<PoiDto.PoiTheme> listPoiCategories() {
    return poiThemeRepository.findAll().stream()
            .map(poiThemeMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Admin
  @GetMapping("/theme/{themeId}")
  public PoiDto.PoiTheme getTheme(@PathVariable("themeId") String themeId) {
    return poiThemeMapper.deepMap(
            poiThemeRepository
                    .findById(themeId)
                    .orElseThrow(ApiClientException.NOT_FOUND::getThrowable)
    );
  }

  //endregion

}
