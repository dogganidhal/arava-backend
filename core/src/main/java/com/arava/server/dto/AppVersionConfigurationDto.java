package com.arava.server.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:57
 * All rights reserved.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppVersionConfigurationDto {

  private Integer minVersion;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime maxDate;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean force;

}
