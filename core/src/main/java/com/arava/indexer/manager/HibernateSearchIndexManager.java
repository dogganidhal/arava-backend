package com.arava.indexer.manager;

import com.arava.indexer.query.SearchQuery;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.repository.PoiRepository;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.engine.spatial.GeoPoint;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 16:29
 * All rights reserved.
 */


@Component
public class HibernateSearchIndexManager implements SearchIndexManager {

  @Autowired
  EntityManager entityManager;

  @Autowired
  EntityManagerFactory entityManagerFactory;

  @Autowired
  private PoiRepository poiRepository;

  @Value("${arava.search.max-similar-results}")
  private Integer maxSimilarResults;

  @Value("${arava.search.indexer-thead-count}")
  private Integer indexerThreadCount;

  @Override
  public void reindexAll() throws InterruptedException {
    Search.session(entityManager)
            .massIndexer()
            .threadsToLoadObjects(indexerThreadCount)
            .startAndWait();
  }

  @Override
  public List<Poi> searchPois(SearchQuery searchQuery) {

    SearchSession searchSession = Search.session(entityManager);

    return searchSession.search(Poi.class)
            .where(predicate -> buildPredicate(searchQuery, predicate))
            .sort(SearchSortFactory::score)
            .fetchAllHits();

  }

  @Override
  public List<Poi> getSimilarPois(String poiId) {

    SearchSession searchSession = Search.session(entityManager);
    Poi poi = poiRepository
            .findById(poiId)
            .orElseThrow(() -> new EntityNotFoundException(
                    String.format("No poi with id '%s' exists", poiId)
            ));

    return searchSession.search(Poi.class)
            .where(f -> f
                    .match()
                    .fields("island.name", "theme.name.resource")
                    .matching(poi)
                    .fuzzy()
            )
            .fetchHits(maxSimilarResults);

  }

  private PredicateFinalStep buildPredicate(SearchQuery query, SearchPredicateFactory factory) {
    BooleanPredicateClausesStep<?> predicate = factory.bool()
            .must(factory.match()
                    .field("disabled")
                    .matching(false)
            )
            .must(factory.match()
                    .field("draft")
                    .matching(false)
            );

    if (query.isEmpty()) {
      predicate
              .must(factory.match()
                      .field("thingsToDo")
                      .matching(true)
              );
    }

    if (query.getRegion() != null) {
      predicate
              .must(factory.spatial()
                      .within()
                      .field("coordinate")
                      .circle(GeoPoint.of(query.getRegion().getCenterLatitude(),
                              query.getRegion().getCenterLongitude()
                      ), query.getRegion().getDistance())
              );
    }

    if (query.getQuery() != null) {
      predicate
              .should(factory.match()
                      .field("title.resource")
                      .matching(query.getQuery())
                      .boost(2)
              )
              .should(factory.match()
                      .field("description.resource")
                      .matching(query.getQuery())
                      .boost(0.1f)
              );
    }

    if (query.getIslandId() != null) {
      predicate
              .must(factory.match()
                      .field("island.id")
                      .matching(query.getIslandId())
              );
    }

    if (query.getThemeId() != null) {
      predicate
              .must(factory.match()
                      .field("theme.id")
                      .matching(query.getThemeId())
              );
    }

    return predicate;
  }

}
