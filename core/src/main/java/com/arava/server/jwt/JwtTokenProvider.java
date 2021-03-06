package com.arava.server.jwt;

import com.arava.persistence.entity.RefreshToken;
import com.arava.persistence.repository.RefreshTokenRepository;
import com.arava.server.dto.response.JwtAuthenticationResponse;
import com.arava.server.exception.ApiClientException;
import com.arava.server.exception.ApiThrowable;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 23:28
 * All rights reserved.
 */

@Slf4j
@Component
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.token-type}")
  private String tokenType;

  @Value("${jwt.expiration}")
  private Long jwtExpiration;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  public String generateToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    return generateForUser(userPrincipal.getUsername());

  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

    return claims.getSubject();
  }

  public JwtAuthenticationResponse refresh(String refreshToken) throws ApiThrowable {
    Optional<RefreshToken> token = refreshTokenRepository.findById(refreshToken);
    if (!token.isPresent()) {
      throw ApiClientException.MISSING_CREDENTIALS
              .getThrowable();
    }
    return JwtAuthenticationResponse.builder()
            .accessToken(generateForUser(token.get().getUser().getEmail()))
            .tokenType(tokenType)
            .expiresIn(jwtExpiration)
            .refreshToken(refreshToken)
            .isAdmin(token.get().getUser().getRoles().stream()
                    .anyMatch(role -> role.getName().toLowerCase().contains("admin"))
            )
            .build();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  private String generateForUser(String username) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpiration * 1000);

    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
  }

}