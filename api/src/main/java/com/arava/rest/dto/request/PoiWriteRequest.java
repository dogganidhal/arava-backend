package com.arava.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 20:50
 * All rights reserved.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiWriteRequest {

  private String id;

  @NotNull
  private Details details;

  @NotNull
  private List<LocalizedDescription> descriptions;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotNull
  private List<MediaWriteRequest> medias;

  @NotNull
  private String islandId;

  @Builder.Default
  private Boolean sponsored = false;

  @Builder.Default
  private Boolean thingsToDo = false;

  @NotNull
  private String categoryId;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LocalizedDescription {

    private String id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String languageCode;

  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Details {

    private String id;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String facebookUrl;
    private String instagramUrl;
    private LocalTime openingHour;
    private LocalTime closingHour;

  }

}
