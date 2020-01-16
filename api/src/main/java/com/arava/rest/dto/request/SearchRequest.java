package com.arava.rest.dto.request;

import com.arava.indexer.query.SearchQuery;
import com.arava.persistence.entity.Island;
import com.arava.rest.dto.LatLng;
import lombok.*;
import org.springframework.data.geo.Point;

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
  private Island island;
  private String category;
  private Region region;
  private SearchSort sort;

  public SearchQuery searchQuery() {
    return SearchQuery.builder()
            .title(title)
            .category(category)
            .island(island.name())
            .region(SearchQuery.Region.builder()
                    .center(new Point(
                            region.getCenter().getLatitude(),
                            region.getCenter().getLongitude())
                    )
                    .distance(region.getDistance())
                    .build()
            )
            .sort(SearchQuery.QuerySort.builder()
                    .direction(sort.getDirection().querySortDirection())
                    .field(sort.getField())
                    .build()
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
