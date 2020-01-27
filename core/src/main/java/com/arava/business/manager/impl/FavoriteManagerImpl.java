package com.arava.business.manager.impl;

import com.arava.business.manager.FavoriteManager;
import com.arava.persistence.entity.Favorite;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.User;
import com.arava.persistence.repository.FavoriteRepository;
import com.arava.persistence.repository.PoiRepository;
import com.arava.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 10:11
 * All rights reserved.
 */


@Component
public class FavoriteManagerImpl implements FavoriteManager {

  @Autowired
  private FavoriteRepository favoriteRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PoiRepository poiRepository;

  @Override
  public void createByUserIdAndPoiId(String userId, String poiId) {
    User user = userRepository.getOne(userId);
    Poi poi = poiRepository.getOne(poiId);
    Favorite favorite = Favorite.builder()
            .poi(poi)
            .user(user)
            .build();
    favoriteRepository.save(favorite);
  }

  @Override
  public void synchronizeUserFavorites(List<String> poiIds, String userId) {
    User user = userRepository.getOne(userId);
    List<Favorite> favorites = poiIds.parallelStream()
            .map(poiId -> favoriteRepository
                    .findByUserIdAndPoiId(userId, poiId)
                    .orElseGet(() -> Favorite.builder()
                            .user(user)
                            .poi(poiRepository.getOne(poiId))
                            .build()
                    )
            )
            .collect(Collectors.toList());
    favoriteRepository.saveAll(favorites);
  }

}