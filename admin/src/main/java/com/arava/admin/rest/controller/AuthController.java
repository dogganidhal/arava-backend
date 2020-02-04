package com.arava.admin.rest.controller;

import com.arava.business.manager.AccessManager;
import com.arava.server.dto.request.LoginRequest;
import com.arava.server.dto.request.RefreshAuthRequest;
import com.arava.server.dto.response.JwtAuthenticationResponse;
import com.arava.server.exception.ApiClientException;
import com.arava.server.exception.ApiThrowable;
import com.arava.server.jwt.JwtTokenProvider;
import com.arava.server.jwt.UserPrincipal;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:21
 * All rights reserved.
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Value("${jwt.token-type}")
  private String tokenType;

  @Value("${jwt.expiration}")
  private Long jwtExpiration;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private AccessManager accessManager;

  @PostMapping("/login")
  public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws ApiThrowable {

    try {
      return authenticate(loginRequest.getEmail(), loginRequest.getPassword());
    } catch (Exception e) {
      throw ApiClientException.BAD_CREDENTIALS
              .getThrowable();
    }

  }

  @SneakyThrows
  @PostMapping("/refresh")
  public JwtAuthenticationResponse refresh(@Valid @RequestBody RefreshAuthRequest request) {
    String jwt = tokenProvider.refresh(request.getRefreshToken());
    return JwtAuthenticationResponse.builder()
            .accessToken(jwt)
            .tokenType(tokenType)
            .expiresIn(jwtExpiration)
            .refreshToken(request.getRefreshToken())
            .build();
  }

  private JwtAuthenticationResponse authenticate(String email, String password) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
    );

    boolean hasAdminAuthority = authentication.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

    if (!hasAdminAuthority) {
      throw ApiClientException.UNAUTHORIZED
              .getThrowable();
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

    String jwt = tokenProvider.generateToken(authentication);
    String refreshToken = accessManager.generateRefreshTokenById(principal.getId()).getToken();

    return JwtAuthenticationResponse.builder()
            .accessToken(jwt)
            .tokenType(tokenType)
            .expiresIn(jwtExpiration)
            .refreshToken(refreshToken)
            .build();
  }

}