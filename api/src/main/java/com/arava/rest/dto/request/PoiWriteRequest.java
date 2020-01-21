package com.arava.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
  @NotBlank
  private Details details;
  @NotBlank
  private List<LocalizedDescription> descriptions;
  @NotBlank
  private Double latitude;
  @NotBlank
  private Double longitude;
  @NotBlank
  private List<MediaWriteRequest> medias;
  @NotBlank
  private String islandId;
  @Builder.Default
  private Boolean sponsored = false;
  @Builder.Default
  private Boolean thingsToDo = false;
  @NotBlank
  private String categoryId;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LocalizedDescription {

    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
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
