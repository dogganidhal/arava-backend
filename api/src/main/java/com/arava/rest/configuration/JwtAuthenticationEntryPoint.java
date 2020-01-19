package com.arava.rest.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 23:42
 * All rights reserved.
 */

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
          HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
          AuthenticationException e) throws IOException {
    log.error("Responding with unauthorized error. Message - {}", e.getMessage());
    httpServletResponse.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "Authentication needed for the requested resource"
    );
  }
}