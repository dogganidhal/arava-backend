package com.arava.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 19/01/2020 23:03
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiThrowable extends RuntimeException {

  private HttpStatus status;
  private String message;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime timestamp;

}
