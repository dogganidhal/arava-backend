package com.arava.rest.controller;

import com.arava.indexer.manager.SearchIndexManager;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.repository.PoiRepository;
import com.arava.rest.dto.PoiDto;
import com.arava.rest.dto.request.SearchRequest;
import com.arava.rest.dto.response.SearchResponse;
import com.arava.rest.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 22:06
 * All rights reserved.
 */

@Slf4j
@RestController
@RequestMapping("/api/search")
public class SearchController {

  @Autowired
  private SearchIndexManager searchIndexManager;

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @PostMapping("/index")
  public void reindexSearch() {
    try {
      searchIndexManager.reindexAll();
    } catch (InterruptedException e) {
      log.error(e.getLocalizedMessage(), e);
    }
  }

  @PostMapping
  public SearchResponse searchPois(@RequestBody SearchRequest request) {
    List<PoiDto> pois = poiRepository.findAll()
            .stream()
            .map(poiMapper::map)
            .collect(Collectors.toList());
    return SearchResponse.builder()
            .count(pois.size())
            .pois(pois)
            .build();
  }

}
