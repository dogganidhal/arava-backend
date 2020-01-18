package com.arava.indexer.manager;

import com.arava.indexer.query.SearchQuery;
import com.arava.persistence.entity.Poi;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 16:29
 * All rights reserved.
 */


@Component
public class HSSearchIndexManager implements SearchIndexManager {

  private FullTextEntityManager fullTextEntityManager;

  @Autowired
  public HSSearchIndexManager(EntityManagerFactory entityManagerFactory) {
    fullTextEntityManager = Search.getFullTextEntityManager(
            entityManagerFactory.createEntityManager()
    );
  }

  @Override
  public void reindexAll() throws InterruptedException {
    fullTextEntityManager.createIndexer().startAndWait();
  }

  @Override
  public void indexOrUpdate(Poi poi) {

  }

  @Override
  public List<Poi> searchPois(SearchQuery query) {
    return null;
  }

}
