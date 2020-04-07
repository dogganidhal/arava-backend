package com.arava.admin.rest.manager.impl;

import com.arava.admin.rest.manager.PoiThemeManager;
import com.arava.admin.rest.mapper.WritePoiThemeMapper;
import com.arava.persistence.entity.PoiTheme;
import com.arava.persistence.repository.PoiThemeRepository;
import com.arava.server.dto.request.PoiThemeWriteRequest;
import com.arava.server.exception.ApiClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 07/04/2020 03:03
 * All rights reserved.
 */


@Component
public class PoiThemeManagerImpl implements PoiThemeManager {

  @Autowired
  private WritePoiThemeMapper writePoiThemeMapper;

  @Autowired
  private PoiThemeRepository poiThemeRepository;

  @Override
  public PoiTheme getById(String id) {
    return poiThemeRepository
            .findById(id)
            .orElseThrow(ApiClientException.THEME_NOT_FOUND::getThrowable);
  }

  @Override
  public void createPoiTheme(PoiThemeWriteRequest request) {
    PoiTheme poiCategory = writePoiThemeMapper.deepMap(request);
    poiThemeRepository.save(poiCategory);
  }

  @Override
  public List<PoiTheme> listPoiThemes() {
    return poiThemeRepository.findAll();
  }

  @Override
  public void editPoiTheme(PoiThemeWriteRequest request) {
    PoiTheme poiCategory = writePoiThemeMapper.deepMap(request);
    poiThemeRepository.save(poiCategory);
  }

  @Override
  public void deletePoiTheme(String id) {
    PoiTheme theme = poiThemeRepository
            .findById(id)
            .orElseThrow(ApiClientException.THEME_NOT_FOUND::getThrowable);
    theme.setDisabled(true);
    poiThemeRepository.save(theme);
    theme.getSubThemes()
            .forEach(subTheme -> deletePoiTheme(subTheme.getId()));
  }

}
