package com.arava.indexer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 21:37
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "indexed_poi")
public class IndexedPoi {

  @Id
  private String id;
  private Point coordinate;
  private String island;
  private String category;
  private List<LocalizedDescription> localizedDescriptions;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LocalizedDescription {

    private String title;
    private String description;
    private String languageCode;

  }

}
