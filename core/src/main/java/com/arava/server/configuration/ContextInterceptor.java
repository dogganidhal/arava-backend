package com.arava.server.configuration;

import com.arava.server.context.ContextResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

/**
 * Created by Nidhal Dogga
 * Date : 31/01/2020 19:04
 * All rights reserved.
 */

@Component
public class ContextInterceptor extends OncePerRequestFilter {

  @Autowired
  private ContextResolver contextResolver;

  @Override
  protected void doFilterInternal(
          HttpServletRequest request, HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {
    String acceptLanguage = request.getHeader(ACCEPT_LANGUAGE);
    if (acceptLanguage != null) {
      contextResolver.setLanguageCode(acceptLanguage);
    }
    doFilter(request, response, filterChain);
  }

}
