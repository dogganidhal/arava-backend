package com.arava.server.exception;

import com.arava.server.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 06:48
 * All rights reserved.
 */


@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler implements ActionListener<IndexResponse> {

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  private RestHighLevelClient esClient;

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
    logInternalServerErrorToElasticSearch(((ServletWebRequest) request).getRequest(), exception);
    return new ResponseEntity<>(
            ErrorResponse.fromApiException(
                    ApiServerException.INTERNAL_SERVER_ERROR.getThrowable(),
                    ((ServletWebRequest) request).getRequest().getServletPath()
            ),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  @SneakyThrows
  private void logInternalServerErrorToElasticSearch(HttpServletRequest request, Exception exception) {
    // TODO: Refactor this logic
    IndexRequest indexRequest = new IndexRequest("com.arava.error");
    indexRequest.id(UUID.randomUUID().toString());
    Map<String, Object> requestMap = new HashMap<String, Object>() {{
      put("Application", applicationName);
      put("Stacktrace", Arrays.stream(exception.getStackTrace())
              .map(StackTraceElement::toString)
              .collect(Collectors.joining(System.lineSeparator()))
      );
      put("Exception", exception.toString());
      put("URL", request.getRequestURL());
      put("Method", request.getMethod());
      put("Timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
      put("Headers", Collections.list(request.getHeaderNames()).stream()
              .reduce(new HashMap<String, String>(),
                      (headersMap, headerKey) -> new HashMap<String, String>() {{
                        put(headerKey, request.getHeader(headerKey));
                      }},
                      (previous, current) -> new HashMap<String, String>() {{
                        putAll(current);
                        putAll(previous);
                      }})
      );
      put("IP", request.getRemoteAddr());
      put("Request", request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
      if (request.getHeader("User-Agent") != null) {
        put("User-Agent", request.getHeader("User-Agent"));
      }
    }};
    String jsonString = new ObjectMapper().writeValueAsString(requestMap);
    indexRequest.source(jsonString, XContentType.JSON);
    esClient.indexAsync(indexRequest, RequestOptions.DEFAULT, this);
  }


  @Override
  public void onResponse(IndexResponse indexResponse) {
    log.info("Error with id {} was logged. \n Content : {}",
            indexResponse.getId(),
            indexResponse.getResult());
  }

  @Override
  public void onFailure(Exception e) {
    log.error("Failed to log error to elastic search. \n {}", e.getLocalizedMessage());
  }

}
