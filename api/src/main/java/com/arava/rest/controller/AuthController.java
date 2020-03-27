package com.arava.rest.controller;

import com.arava.business.manager.AccessManager;
import com.arava.persistence.entity.User;
import com.arava.persistence.repository.UserRepository;
import com.arava.server.jwt.JwtTokenProvider;
import com.arava.server.jwt.UserPrincipal;
import com.arava.server.dto.request.LoginRequest;
import com.arava.server.dto.request.RefreshAuthRequest;
import com.arava.rest.dto.request.SignUpRequest;
import com.arava.server.dto.response.JwtAuthenticationResponse;
import com.arava.server.exception.ApiClientException;
import com.arava.server.exception.ApiThrowable;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 23:36
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
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

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
  @PostMapping("/signup")
  public ResponseEntity<JwtAuthenticationResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    if(userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw ApiClientException.USER_EXISTS
              .getThrowable();
    }

    User user = User.builder()
            .email(signUpRequest.getEmail())
            .firstName(signUpRequest.getFirstName())
            .lastName(signUpRequest.getLastName())
            .passwordHash(passwordEncoder.encode(signUpRequest.getPassword()))
            .build();

    User result = userRepository.save(user);

    URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/api/users/{username}")
            .buildAndExpand(user.getId())
            .toUri();

    JwtAuthenticationResponse authenticationResponse = authenticate(
            result.getEmail(), signUpRequest.getPassword()
    );
    return ResponseEntity.created(location).body(authenticationResponse);
  }

  @SneakyThrows
  @PostMapping("/refresh")
  public JwtAuthenticationResponse refresh(@Valid @RequestBody RefreshAuthRequest request) {
    return tokenProvider.refresh(request.getRefreshToken());
  }

  private JwtAuthenticationResponse authenticate(String email, String password) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
    );

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