package com.arava.server.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * Created by Nidhal Dogga
 * Date : 09/02/2020 16:35
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiDetailsDto {

  private String address;
  private String phone;
  private String email;
  private String website;
  private String facebookUrl;
  private String instagramAccount;
  @JsonSerialize(using = LocalTimeSerializer.class)
  private LocalTime openingHour;
  @JsonSerialize(using = LocalTimeSerializer.class)
  private LocalTime closingHour;

}
