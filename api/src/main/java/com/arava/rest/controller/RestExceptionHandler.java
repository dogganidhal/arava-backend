package com.arava.rest.controller;

import com.arava.rest.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 06:48
 * All rights reserved.
 */


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ ApiException.class })
  public ResponseEntity<ApiException> handleApiException(ApiException exception, WebRequest request) {
    return new ResponseEntity<>(
            exception,
            new HttpHeaders(),
            exception.getStatus()
    );
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<ApiException> handleInternalException(Exception exception, WebRequest request) {
    log.error(exception.getLocalizedMessage(), exception);
    return new ResponseEntity<>(
            ApiException.INTERNAL_SERVER_ERROR,
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

}
