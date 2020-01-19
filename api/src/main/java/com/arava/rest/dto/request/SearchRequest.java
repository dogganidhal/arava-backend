package com.arava.rest.dto.request;

import com.arava.indexer.query.SearchQuery;
import com.arava.rest.dto.LatLng;
import lombok.*;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 23:40
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

  private String title;
  private String island;
  private String category;
  private Region region;
  private SearchSort sort;

  public SearchQuery searchQuery() {
    return SearchQuery.builder()
            .title(title)
            .category(category)
            .island(island)
            .region(region != null ?
                    SearchQuery.Region.builder()
                            .centerLatitude(region.getCenter().getLatitude())
                            .centerLongitude(region.getCenter().getLongitude())
                            .distance(region.getDistance())
                            .build() :
                    null
            )
            .sort(sort != null ?
                    SearchQuery.QuerySort.builder()
                            .direction(sort.getDirection().querySortDirection())
                            .field(sort.getField())
                            .build() :
                    null
            )
            .build();
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Region {

    private LatLng center;
    private Double distance;

  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class SearchSort {

    private String field;
    private SearchSortDirection direction;

  }

  @Getter
  public enum SearchSortDirection {

    ASC {
      @Override
      public SearchQuery.QuerySortDirection querySortDirection() {
        return SearchQuery.QuerySortDirection.DESC;
      }
    },
    DESC {
      @Override
      public SearchQuery.QuerySortDirection querySortDirection() {
        return SearchQuery.QuerySortDirection.DESC;
      }
    };

    public abstract SearchQuery.QuerySortDirection querySortDirection();

  }

}
