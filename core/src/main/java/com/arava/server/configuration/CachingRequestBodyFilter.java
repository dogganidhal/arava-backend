package com.arava.server.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Nidhal Dogga
 * Date : 10/03/2020 12:04
 * All rights reserved.
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CachingRequestBodyFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
    MultiReadHttpRequest wrappedRequest = new MultiReadHttpRequest(currentRequest);
    chain.doFilter(wrappedRequest, servletResponse);
  }

}