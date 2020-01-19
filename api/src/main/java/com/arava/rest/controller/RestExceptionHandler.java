package com.arava.rest.controller;

import com.arava.rest.dto.response.ErrorResponse;
import com.arava.rest.exception.ApiServerException;
import com.arava.rest.exception.ApiThrowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 06:48
 * All rights reserved.
 */


@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleApiException(ApiThrowable exception, WebRequest request) {
    log.info(exception.toString(), exception);
    return new ResponseEntity<>(
            ErrorResponse.fromApiException(
                    exception,
                    ((ServletWebRequest) request).getRequest().getServletPath()
            ),
            new HttpHeaders(),
            exception.getStatus()
    );
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleUncaughtExceptions(Exception exception, WebRequest request) {
    log.info(exception.toString(), exception);
    return new ResponseEntity<>(
            ErrorResponse.fromApiException(
                    ApiServerException.INTERNAL_SERVER_ERROR.getThrowable(),
                    ((ServletWebRequest) request).getRequest().getServletPath()
            ),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

}
