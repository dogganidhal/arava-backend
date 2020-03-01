package com.arava.server.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 06:46
 * All rights reserved.
 */


public interface ApiException {

  ErrorCode getErrorCode();
  HttpStatus getStatus();
  LocalDateTime getTimestamp();
  ApiThrowable getThrowable();
  default String getMessage() {
    return "Undocumented error";
  }

}
