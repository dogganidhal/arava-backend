package com.arava.rest.controller;

import com.arava.business.manager.FavoriteManager;
import com.arava.persistence.entity.Favorite;
import com.arava.persistence.repository.FavoriteRepository;
import com.arava.rest.annotation.Authenticated;
import com.arava.rest.configuration.UserPrincipal;
import com.arava.rest.dto.FavoriteDto;
import com.arava.rest.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 09:53
 * All rights reserved.
 */

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private FavoriteRepository favoriteRepository;

  @Autowired
  private FavoriteManager favoriteManager;

  @Autowired
  private Mapper<Favorite, FavoriteDto> favoriteMapper;

  @Authenticated
  @GetMapping("/favorite")
  public List<FavoriteDto> getFavorites(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    return favoriteRepository.findByUserId(userPrincipal.getId()).stream()
            .map(favoriteMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Authenticated
  @PostMapping("/favorite/{poiId}")
  public void addToFavorites(@PathVariable("poiId") String poiId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
    favoriteManager.createByUserIdAndPoiId(userPrincipal.getId(), poiId);
  }

  @Authenticated
  @PutMapping("/favorite")
  public void synchronizeFavorites(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody List<String> poiIds) {
    favoriteManager.synchronizeUserFavorites(poiIds, userPrincipal.getId());
  }

}
