package com.arava.indexer.manager;

import com.arava.indexer.query.SearchQuery;
import com.arava.persistence.entity.Poi;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 22:06
 * All rights reserved.
 */

public interface SearchIndexManager {

  void reindexAll() throws InterruptedException;
  void indexOrUpdate(Poi poi);
  List<Poi> searchPois(SearchQuery query);

}
