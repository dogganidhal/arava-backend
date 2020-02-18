package com.arava.server.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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
  private Map<String, String> title;

  @NotNull
  private Map<String, String> description;

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
  private Boolean featured = false;

  @Builder.Default
  private Boolean thingsToDo = false;

  @Builder.Default
  private Boolean draft = true;

  @NotNull
  private String themeId;

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
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime openingHour;
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime closingHour;

  }

}
