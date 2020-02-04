package com.arava.rest.controller;

import com.arava.persistence.entity.Poi;
import com.arava.persistence.repository.PoiRepository;
import com.arava.rest.dto.PoiDto;
import com.arava.server.exception.ApiClientException;
import com.arava.server.mapper.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  private Mapper<Poi, PoiDto> poiMapper;

  //region Poi CRUD operations

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

  @SneakyThrows
  @GetMapping("/{poiId}")
  public PoiDto getPoi(@PathVariable("poiId") String poiId) {
    return poiMapper.deepMap(poiRepository
            .findById(poiId)
            .orElseThrow(ApiClientException.NOT_FOUND::getThrowable)
    );
  }

  //endregion

}
