package com.arava.persistence.repository;

import com.arava.business.manager.CacheManager;
import com.arava.persistence.entity.AppVersionConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 07:39
 * All rights reserved.
 */

public interface AppVersionConfigurationRepository extends JpaRepository<AppVersionConfiguration, String> {

  @Cacheable(CacheManager.appVersionConfigurationCache)
  AppVersionConfiguration findTop1ByOrderByCreatedDesc();

  default AppVersionConfiguration getLatestRevision() {
    return findTop1ByOrderByCreatedDesc();
  }

}
