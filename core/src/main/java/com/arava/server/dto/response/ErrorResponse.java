package com.arava.server.dto.response;

import com.arava.server.exception.ApiThrowable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 17:55
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  private HttpStatus statusCode;
  @Builder.Default
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime timestamp = LocalDateTime.now();
  private String errorCode;
  private String message;
  private Integer status;
  private String path;

  public static ErrorResponse fromApiException(ApiThrowable exception, String path) {
    return ErrorResponse.builder()
            .status(exception.getStatus().value())
            .statusCode(exception.getStatus())
            .timestamp(exception.getTimestamp())
            .message(exception.getMessage())
            .errorCode(exception.getErrorCode().name())
            .path(path)
            .build();
  }

}
