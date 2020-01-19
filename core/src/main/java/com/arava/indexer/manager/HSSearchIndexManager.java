package com.arava.indexer.manager;

import com.arava.indexer.query.SearchQuery;
import com.arava.persistence.entity.Poi;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.engine.ProjectionConstants;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
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
  public List<Poi> searchPois(SearchQuery searchQuery) {
    return executeLuceneQuery(searchQuery);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Poi> getSimilarPois(String poiId) {

    QueryBuilder qb = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder()
            .forEntity(Poi.class)
            .get();

    Query mltQuery = qb
            .moreLikeThis()
            .comparingAllFields()
            .toEntityWithId(poiId)
            .createQuery();

    return (List<Poi>) fullTextEntityManager
            .createFullTextQuery(mltQuery, Poi.class)
            .setProjection(ProjectionConstants.THIS, ProjectionConstants.SCORE)
            .getResultList();
  }

  @SuppressWarnings("unchecked")
  private List<Poi> executeLuceneQuery(SearchQuery searchQuery) {

    BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();

    if (searchQuery.getTitle() != null) {
      queryBuilder.add(
              createQueryBuilder()
                      .keyword()
                      .fuzzy()
                      .onField("localizedDescriptions.title")
                      .matching(searchQuery.getTitle())
                      .createQuery(),
              BooleanClause.Occur.MUST
      );
    }

    if (searchQuery.getCategory() != null) {
      queryBuilder.add(createQueryBuilder()
              .keyword()
              .onField("category.name")
              .matching(searchQuery.getCategory())
              .createQuery(),
              BooleanClause.Occur.MUST
      );
    }

    if (searchQuery.getIsland() != null) {
      queryBuilder.add(
              createQueryBuilder()
                      .keyword()
                      .onField("island")
                      .matching(searchQuery.getIsland())
                      .createQuery(),
              BooleanClause.Occur.MUST
      );
    }

    if (searchQuery.getRegion() != null) {
      queryBuilder.add(
              createQueryBuilder()
                      .spatial()
                      .onField("latitude")
                      .within(searchQuery.getRegion().getDistance(), Unit.KM)
                      .ofLatitude(searchQuery.getRegion().getCenterLatitude())
                      .andLongitude(searchQuery.getRegion().getCenterLongitude())
                      .createQuery(),
              BooleanClause.Occur.MUST
      );
    }

    FullTextQuery query = fullTextEntityManager.createFullTextQuery(queryBuilder.build(), Poi.class);

    String sortField = "id";
    SearchQuery.QuerySortDirection sortDirection = SearchQuery.QuerySortDirection.ASC;

    if (searchQuery.getSort() != null) {
      sortField = searchQuery.getSort().getField();
      sortDirection = searchQuery.getSort().getDirection();
    }

    // TODO: Figure out how to do sort

//    query.setSort(createQueryBuilder()
//            .sort()
//            .byField(sortField)
//            .asc()
//            .createSort()
//    );

    List<Poi> result = (List<Poi>) query.getResultList();
    if (sortDirection == SearchQuery.QuerySortDirection.DESC) {
      Collections.reverse(result);
    }
    return result;

  }

  private QueryBuilder createQueryBuilder() {
    return fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder()
            .forEntity(Poi.class)
            .get();
  }

}
