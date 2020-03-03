package com.arava.server;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Nidhal Dogga
 * Date : 27/02/2020 08:23
 * All rights reserved.
 */

@Slf4j
@Component
public class Indexer implements CommandLineRunner {

  @Autowired
  EntityManagerFactory entityManagerFactory;

  @Override
  public void run(String... args) {
    log.info("Starting indexer");
    Search.session(entityManagerFactory.createEntityManager())
            .massIndexer()
            .start()
            .thenRun((() -> log.info("Indexer started successfully")))
            .exceptionally(exception -> {
              log.error("Indexer crashed with exception {}", exception.getLocalizedMessage());
              return null;
            });
  }

}
