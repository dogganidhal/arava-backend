package com.arava.persistence.repository;

import com.arava.persistence.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 09:57
 * All rights reserved.
 */

public interface FavoriteRepository extends JpaRepository<Favorite, String> {

  @Query("SELECT f FROM Favorite f WHERE f.disabled = FALSE OR f.disabled IS NULL")
  List<Favorite> findByUserId(String userId);

}
