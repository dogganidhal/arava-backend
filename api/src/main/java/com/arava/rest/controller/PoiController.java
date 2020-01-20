package com.arava.rest.controller;

import com.arava.persistence.entity.Poi;
import com.arava.persistence.repository.PoiRepository;
import com.arava.rest.annotation.Admin;
import com.arava.rest.dto.request.CreatePoiRequest;
import com.arava.rest.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 20:49
 * All rights reserved.
 */


@RestController
@RequestMapping("/api/poi")
public class PoiController {

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private Mapper<CreatePoiRequest, Poi> poiMapper;

  @Admin
  @PostMapping
  public void createPoi(@RequestBody CreatePoiRequest request) {
    poiRepository.save(poiMapper.map(request));
  }

}
