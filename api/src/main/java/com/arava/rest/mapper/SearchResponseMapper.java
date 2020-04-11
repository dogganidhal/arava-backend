package com.arava.rest.mapper;

import com.arava.persistence.entity.Poi;
import com.arava.rest.dto.PoiDto;
import com.arava.rest.dto.response.SearchResponse;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 11/04/2020 18:18
 * All rights reserved.
 */

@Component
public class SearchResponseMapper implements Mapper<List<Poi>, SearchResponse> {

  // TODO: Move to spring property ?
  private static final Integer MAX_PREMIUM_RESULTS = 6;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @Override
  public SearchResponse deepMap(List<Poi> objects) {
    List<Poi> pois = objects.stream()
            .filter(poi -> !poi.isPremium())
            .collect(Collectors.toList());
    List<Poi> premiumPois = objects.stream()
            .filter(Poi::isPremium)
            .collect(Collectors.toList());
    return SearchResponse.builder()
            .count(pois.size())
            .pois(pois.stream()
                    .map(poiMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .premiumCount(Math.min(premiumPois.size(), MAX_PREMIUM_RESULTS))
            .premiumPois(premiumPois.stream()
                    .limit(MAX_PREMIUM_RESULTS)
                    .map(poiMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .build();
  }
}
