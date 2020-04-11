package com.arava.rest.controller;

import com.arava.indexer.manager.SearchIndexManager;
import com.arava.persistence.entity.Poi;
import com.arava.rest.dto.request.SearchRequest;
import com.arava.rest.dto.response.SearchResponse;
import com.arava.server.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 22:06
 * All rights reserved.
 */

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {

  @Autowired
  private SearchIndexManager searchIndexManager;

  @Autowired
  private Mapper<List<Poi>, SearchResponse> searchResponseMapper;

  @PostMapping
  public SearchResponse searchPois(@RequestBody SearchRequest request) {
    List<Poi> pois = searchIndexManager.searchPois(request.searchQuery());
    return searchResponseMapper.deepMap(pois);
  }

  @GetMapping("/similar/{poiId}")
  public SearchResponse getSimilar(@PathVariable("poiId") String poiId) {
    List<Poi> pois = searchIndexManager.getSimilarPois(poiId);
    return searchResponseMapper.deepMap(pois);
  }

}
