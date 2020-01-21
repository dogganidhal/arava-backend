package com.arava.rest.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 19/01/2020 22:57
 * All rights reserved.
 */

public enum ApiClientException implements ApiException {

  BAD_CREDENTIALS {

    @Override
    public HttpStatus getStatus() {
      return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getMessage() {
      return "Bad credentials";
    }

  },
  UNAUTHORIZED {

    @Override
    public HttpStatus getStatus() {
      return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getMessage() {
      return "Authorization required to access requested resource";
    }

  },
  USER_EXISTS {

    @Override
    public HttpStatus getStatus() {
      return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
      return "User exists";
    }

  },
  NOT_FOUND {

    @Override
    public HttpStatus getStatus() {
      return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
      return "No entity with specified ids was found";
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
