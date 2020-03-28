package com.arava.admin.rest.controller;

import com.arava.admin.rest.dto.PoiDto;
import com.arava.admin.rest.manager.PoiManager;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.PoiTheme;
import com.arava.persistence.repository.PoiRepository;
import com.arava.persistence.repository.PoiThemeRepository;
import com.arava.server.annotation.Admin;
import com.arava.server.annotation.Authenticated;
import com.arava.server.dto.request.PoiThemeWriteRequest;
import com.arava.server.dto.request.PoiWriteRequest;
import com.arava.server.exception.ApiClientException;
import com.arava.server.jwt.UserPrincipal;
import com.arava.server.mapper.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

  @Autowired
  private PoiManager poiManager;

  //region Poi CRUD operations
  
  @Authenticated
  @GetMapping("/{poiId}")
  public PoiDto getPoi(
          @AuthenticationPrincipal UserPrincipal userPrincipal,
          @PathVariable("poiId") String poiId) {
    Poi poi = poiManager.getById(userPrincipal, poiId);
    return poiMapper.deepMap(poi);
  }

  @Authenticated
  @SneakyThrows
  @GetMapping
  public List<PoiDto> listPois(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    return poiManager.listPois(userPrincipal).stream()
            .map(poiMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Admin
  @PostMapping
  public void createPoi(@Valid @RequestBody PoiWriteRequest request) {
    poiRepository.save(writePoiMapper.deepMap(request));
  }

  @Authenticated
  @PutMapping
  public void updatePoi(
          @AuthenticationPrincipal UserPrincipal userPrincipal,
          @Valid @RequestBody PoiWriteRequest request) {
    poiManager.editPoi(userPrincipal, request);
  }

  @Authenticated
  @SneakyThrows
  @DeleteMapping("/{poiId}")
  public void deletePoi(
          @AuthenticationPrincipal UserPrincipal userPrincipal,
          @PathVariable("poiId") String poiId) {
    poiManager.deletePoi(userPrincipal, poiId);
  }

  @Authenticated
  @PostMapping("/{poiId}/toggle-draft")
  public void toggleDraft(
          @AuthenticationPrincipal UserPrincipal userPrincipal,
          @PathVariable("poiId") String poiId) {
    poiManager.toggleDraft(userPrincipal, poiId);
  }

  //endregion

  //region Poi type / category CRUD operations

  @Admin
  @PostMapping("/theme")
  public void createPoiTheme(@Valid @RequestBody PoiThemeWriteRequest request) {
    PoiTheme poiCategory = writePoiThemeMapper.deepMap(request);
    poiThemeRepository.save(poiCategory);
  }

  @Admin
  @PutMapping("/theme")
  public void updatePoiTheme(@Valid @RequestBody PoiThemeWriteRequest request) {
    PoiTheme poiCategory = writePoiThemeMapper.deepMap(request);
    poiThemeRepository.save(poiCategory);
  }

  @Admin
  @GetMapping("/theme")
  public List<PoiDto.PoiTheme> listPoiThemes() {
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
                    .orElseThrow(ApiClientException.THEME_NOT_FOUND::getThrowable)
    );
  }

  @Admin
  @DeleteMapping("/theme/{themeId}")
  public void deleteTheme(@PathVariable("themeId") String themeId) {
    PoiTheme theme = poiThemeRepository
            .findById(themeId)
            .orElseThrow(ApiClientException.THEME_NOT_FOUND::getThrowable);
    theme.setDisabled(true);
    poiThemeRepository.save(theme);
  }

  //endregion

}
