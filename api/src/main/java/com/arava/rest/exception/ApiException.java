package com.arava.rest.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 06:46
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {

  public static final ApiException INTERNAL_SERVER_ERROR = ApiException.builder()
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .message("Server is having some trouble right now, please come back later")
          .build();

  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();
  private String message;

}
