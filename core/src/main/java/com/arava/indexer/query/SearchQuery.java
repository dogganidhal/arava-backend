package com.arava.indexer.query;

import lombok.*;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 22:06
 * All rights reserved.
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchQuery {

  private String query;
  private Region region;
  private String islandId;
  private List<String> themeIds;
  private QuerySort sort;
  @Getter
  @Builder.Default
  private boolean sponsored = false;

  public boolean isEmpty() {
    return query == null && region == null && islandId == null && (themeIds == null || themeIds.isEmpty());
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Region {

    private Double centerLatitude;

    private Double centerLongitude;

    private Double distance;

  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class QuerySort {

    private String field;

    @Builder.Default
    private QuerySortDirection direction = QuerySortDirection.ASC;

  }

  @Getter
  public enum QuerySortDirection {

    ASC {
      @Override
      public Sort.Direction sort() {
        return Sort.Direction.ASC;
      }
    },
    DESC {
      @Override
      public Sort.Direction sort() {
        return Sort.Direction.DESC;
      }
    };

    public abstract Sort.Direction sort();

  }

}
