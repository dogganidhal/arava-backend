package com.arava.indexer.query;

import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;

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

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class Region {

    private Point center;
    private Double distance;

  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class QuerySort {

    private String field;
    private QuerySortDirection direction;

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
