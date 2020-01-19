package com.arava.rest.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 19/01/2020 23:06
 * All rights reserved.
 */

public enum ApiServerException implements ApiException {

  INTERNAL_SERVER_ERROR {

    @Override
    public HttpStatus getStatus() {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
      return "Server is having some trouble, please come back later";
    }

  };

  @Override
  public LocalDateTime getTimestamp() {
    return LocalDateTime.now();
  }

  @Override
  public ApiThrowable getThrowable() {
    return ApiThrowable.builder()
            .message(getMessage())
            .status(getStatus())
            .timestamp(getTimestamp())
            .build();
  }
}
