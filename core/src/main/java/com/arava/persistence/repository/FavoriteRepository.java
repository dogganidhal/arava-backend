package com.arava.persistence.repository;

import com.arava.persistence.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 09:57
 * All rights reserved.
 */

public interface FavoriteRepository extends JpaRepository<Favorite, String> {

  List<Favorite> findByUserId(String userId);
  Optional<Favorite> findByUserIdAndPoiId(String userId, String poiId);

}
