package com.arava.business.manager;

import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:11
 * All rights reserved.
 */

@Component
public class IslandCacheManager extends SimpleCacheManager {

  public static final String islandByNameCache = "islandByName";
  public static final String allIslandsCache = "allIslands";

  public IslandCacheManager() {
    setCaches(Arrays.asList(
            new ConcurrentMapCache(islandByNameCache),
            new ConcurrentMapCache(allIslandsCache)
    ));
  }

}
