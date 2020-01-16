package com.arava.rest.controller;

import com.arava.persistence.entity.User;
import com.arava.persistence.repository.UserRepository;
import com.arava.rest.configuration.JwtTokenProvider;
import com.arava.rest.dto.request.SignUpRequest;
import com.arava.rest.dto.request.LoginRequest;
import com.arava.rest.dto.response.JwtAuthenticationResponse;
import com.arava.rest.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/auth")
public class AuthController {

  @Value("${jwt.token-type}")
  private String tokenType;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;


  @PostMapping("/login")
  public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws ApiException {

    try {
      return authenticate(loginRequest.getEmail(), loginRequest.getPassword());
    } catch (Exception e) {
      throw ApiException.builder()
              .status(HttpStatus.UNAUTHORIZED)
              .message(e.getLocalizedMessage())
              .build();
    }

  }

  @PostMapping("/signup")
  public ResponseEntity<JwtAuthenticationResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    if(userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw ApiException.builder()
              .status(HttpStatus.BAD_REQUEST)
              .message("Email exists already")
              .build();
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
            result.getEmail(),
            signUpRequest.getPassword()
    );
    return ResponseEntity.created(location).body(authenticationResponse);
  }

  private JwtAuthenticationResponse authenticate(String email, String password) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return JwtAuthenticationResponse.builder()
            .accessToken(jwt)
            .tokenType(tokenType)
            .build();
  }

}