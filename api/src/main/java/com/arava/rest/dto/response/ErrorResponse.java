package com.arava.rest.dto.response;

import com.arava.rest.exception.ApiThrowable;
import com.fasterxml.jackson.annotation.JsonFormat;
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();
  private String message;
  private Integer status;
  private String path;

  public static ErrorResponse fromApiException(ApiThrowable exception, String path) {
    return ErrorResponse.builder()
            .status(exception.getStatus().value())
            .statusCode(exception.getStatus())
            .timestamp(exception.getTimestamp())
            .message(exception.getMessage())
            .path(path)
            .build();
  }

}
