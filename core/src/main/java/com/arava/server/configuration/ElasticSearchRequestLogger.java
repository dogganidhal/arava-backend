package com.arava.server.configuration;

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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 27/02/2020 09:09
 * All rights reserved.
 */

@Slf4j
@ConditionalOnProperty(value = "arava.log-to-elasticsearch", havingValue = "true")
@Component
public class ElasticSearchRequestLogger extends OncePerRequestFilter implements ActionListener<IndexResponse> {

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  private RestHighLevelClient esClient;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    Instant begin = Instant.now();
    filterChain.doFilter(request, response);
    Instant end = Instant.now();
    logRequest(request, response, begin, end);

  }

  @SneakyThrows
  private void logRequest(HttpServletRequest request, HttpServletResponse response, Instant begin, Instant end) {
    IndexRequest indexRequest = new IndexRequest("com.arava.web");
    indexRequest.id(UUID.randomUUID().toString());
    Map<String, Object> requestMap = new HashMap<String, Object>() {{
      put("Application", applicationName);
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
      put("Status", response.getStatus());
      put("Elapsed", Duration.between(begin, end).toMillis());
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
    log.info("Request with id {} was logged. \n Content : {}",
            indexResponse.getId(),
            indexResponse.getIndex());
  }

  @Override
  public void onFailure(Exception e) {
    log.error("Failed to log request to elastic search. \n {}", e.getLocalizedMessage());
  }

}
