package com.arava.admin.rest.controller;

import com.arava.indexer.manager.SearchIndexManager;
import com.arava.server.annotation.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @Admin
  @PostMapping("/index")
  public void reindexSearch() {
    try {
      searchIndexManager.reindexAll();
    } catch (InterruptedException e) {
      log.error(e.getLocalizedMessage(), e);
    }
  }

}
