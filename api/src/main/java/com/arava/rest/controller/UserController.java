package com.arava.rest.controller;

import com.arava.business.manager.AccessManager;
import com.arava.business.manager.FavoriteManager;
import com.arava.persistence.entity.Favorite;
import com.arava.persistence.entity.User;
import com.arava.persistence.repository.FavoriteRepository;
import com.arava.persistence.repository.UserRepository;
import com.arava.rest.dto.FavoriteDto;
import com.arava.rest.dto.UserDto;
import com.arava.server.annotation.Authenticated;
import com.arava.server.dto.request.UpdateProfileRequest;
import com.arava.server.exception.ApiClientException;
import com.arava.server.jwt.UserPrincipal;
import com.arava.server.mapper.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 09:53
 * All rights reserved.
 */

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FavoriteRepository favoriteRepository;

  @Autowired
  private FavoriteManager favoriteManager;

  @Autowired
  private Mapper<User, UserDto> userMapper;

  @Autowired
  private Mapper<Favorite, FavoriteDto> favoriteMapper;

  @Autowired
  private AccessManager accessManager;

  // region Favorites management

  @Authenticated
  @GetMapping("/favorite")
  public List<FavoriteDto> getFavorites(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    return favoriteRepository.findByUserId(userPrincipal.getId()).stream()
            .map(favoriteMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Authenticated
  @PostMapping("/favorite/{poiId}")
  public void addToFavorites(@PathVariable("poiId") String poiId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
    favoriteManager.createByUserIdAndPoiId(userPrincipal.getId(), poiId);
  }

  @Authenticated
  @PutMapping("/favorite")
  public void synchronizeFavorites(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody List<String> poiIds) {
    favoriteManager.synchronizeUserFavorites(poiIds, userPrincipal.getId());
  }

  // endregion

  @SneakyThrows
  @Authenticated
  @DeleteMapping("/access/{refreshToken}")
  public void revokeRefreshToken(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("refreshToken") String refreshToken) {
    try {
      accessManager.revokeRefreshToken(userPrincipal.getId(), refreshToken);
    } catch (IllegalAccessException e) {
      throw ApiClientException.UNAUTHORIZED
              .getThrowable();
    }
  }

  @Authenticated
  @GetMapping
  public UserDto getUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    return userMapper.deepMap(userRepository.findByEmail(userPrincipal.getUsername()));
  }

  @Authenticated
  @PutMapping
  public void getUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid UpdateProfileRequest request) {
    accessManager.updateUserProfile(userPrincipal.getId(), request);
  }

}
