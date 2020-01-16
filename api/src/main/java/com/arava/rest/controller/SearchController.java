package com.arava.rest.controller;

import com.arava.persistence.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping("/api/search")
public class SearchController {

  @PostMapping
  public void searchPois(@AuthenticationPrincipal User user) {
    log.info("Hitting with user {}", user);
  }

}
