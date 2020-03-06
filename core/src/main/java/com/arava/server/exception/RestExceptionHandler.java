package com.arava.server.exception;

import com.arava.server.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 06:48
 * All rights reserved.
 */


@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException exception, HttpHeaders headers,
          HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(
            ErrorResponse.builder()
                    .message(String.format("Value '%s' is not a valid value for field '%s.%s'",
                            Objects.requireNonNull(exception.getBindingResult().getFieldError()).getRejectedValue(),
                            exception.getBindingResult().getFieldError().getObjectName(),
                            exception.getBindingResult().getFieldError().getField()
                    ))
                    .errorCode(ErrorCode.GENERAL_VALIDATION_ERROR.name())
                    .timestamp(LocalDateTime.now())
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .path(((ServletWebRequest) request).getRequest().getServletPath())
                    .build(),
            new HttpHeaders(),
            HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleApiException(ApiThrowable exception, WebRequest request) {
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
  public ResponseEntity<ErrorResponse> handleEntityNotFoundExceptions(
          AuthenticationCredentialsNotFoundException exception, WebRequest request) {
    return new ResponseEntity<>(
            ErrorResponse.fromApiException(
                    ApiClientException.UNAUTHORIZED.getThrowable(),
                    ((ServletWebRequest) request).getRequest().getServletPath()
            ),
            new HttpHeaders(),
            ApiClientException.UNAUTHORIZED.getStatus()
    );
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleAccessDeniedException(
          AccessDeniedException exception, WebRequest request) {
    return new ResponseEntity<>(
            ErrorResponse.fromApiException(
                    ApiClientException.UNAUTHORIZED.getThrowable(),
                    ((ServletWebRequest) request).getRequest().getServletPath()
            ),
            new HttpHeaders(),
            ApiClientException.UNAUTHORIZED.getStatus()
    );
  }

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleUncaughtExceptions(Exception exception, WebRequest request) {
    log.error(exception.toString(), exception);
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
