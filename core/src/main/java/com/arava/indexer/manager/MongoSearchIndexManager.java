package com.arava.indexer.manager;

import com.arava.indexer.entity.IndexedPoi;
import com.arava.indexer.query.SearchQuery;
import com.arava.indexer.repository.IndexedPoiRepository;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 22:22
 * All rights reserved.
 */

@Component
public class MongoSearchIndexManager implements SearchIndexManager {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private IndexedPoiRepository indexedPoiRepository;

  @Override
  public void reindexAll() {

  }

  @Override
  public void indexOrUpdate(Poi poi) {
    IndexedPoi indexedPoi = IndexedPoi.builder()
            .id(poi.getId())
            .category(poi.getCategory().getName())
            .coordinate(poi.getCoordinate())
            .build();
    indexedPoiRepository.save(indexedPoi);
  }

  @Override
  public List<Poi> searchPois(SearchQuery query) {
    Query mongoQuery = query.mongoQuery();
    return mongoTemplate
            .find(mongoQuery, IndexedPoi.class)
            .parallelStream()
            .map(indexedPoi -> poiRepository.findById(indexedPoi.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
  }

}
