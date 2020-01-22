package com.arava.indexer.query;

import lombok.*;
import org.springframework.data.domain.Sort;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 22:06
 * All rights reserved.
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchQuery {

  private String title;
  private Region region;
  private String island;
  private String category;
  private QuerySort sort;

  public boolean isEmpty() {
    return title == null && region == null && island == null && category == null;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class Region {

    private Double centerLatitude;

    private Double centerLongitude;

    private Double distance;

  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
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
