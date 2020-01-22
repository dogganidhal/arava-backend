package com.arava.persistence.repository;

import com.arava.business.manager.CacheManager;
import com.arava.persistence.entity.PoiType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 08:32
 * All rights reserved.
 */

public interface PoiTypeRepository extends JpaRepository<PoiType, String> {

  @Override
  @Cacheable(CacheManager.allPoiTypesCache)
  List<PoiType> findAll();

}
