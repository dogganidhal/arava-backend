package com.arava.persistence.repository;

import com.arava.persistence.entity.Island;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:04
 * All rights reserved.
 */

public interface IslandRepository extends JpaRepository<Island, String> {

  @Query("SELECT COUNT(p.id) FROM Poi p " +
          "WHERE p.island.id = ?1 " +
          "AND p.disabled = FALSE " +
          "AND p.draft = FALSE"
  )
  Integer countActivePois(String islandId);

}
