package com.arava.persistence.repository;

import com.arava.business.manager.IslandCacheManager;
import com.arava.persistence.entity.Island;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:04
 * All rights reserved.
 */

public interface IslandRepository extends JpaRepository<Island, String> {

  @Cacheable(IslandCacheManager.islandByNameCache)
  Island getIslandByName(String name);

  @Cacheable(IslandCacheManager.allIslandsCache)
  List<Island> findAll();

}
