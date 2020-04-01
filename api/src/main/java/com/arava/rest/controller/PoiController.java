package com.arava.rest.controller;

import com.arava.business.manager.ContentManager;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.User;
import com.arava.persistence.repository.PoiRepository;
import com.arava.persistence.repository.UserRepository;
import com.arava.rest.dto.PoiDto;
import com.arava.server.annotation.Authenticated;
import com.arava.server.dto.request.RateCommentRequest;
import com.arava.server.exception.ApiClientException;
import com.arava.server.exception.ApiServerException;
import com.arava.server.jwt.UserPrincipal;
import com.arava.server.mapper.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 20:49
 * All rights reserved.
 */


@RestController
@RequestMapping("/poi")
public class PoiController {

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @Autowired
  private ContentManager contentManager;

  //region Poi CRUD operations

  @SneakyThrows
  @GetMapping
  public List<PoiDto> getAllPois() {
    try {
      return poiRepository.findAll().stream()
              .map(poiMapper::deepMap)
              .collect(Collectors.toList());
    } catch (EntityNotFoundException e) {
      throw ApiClientException.POI_NOT_FOUND
              .getThrowable();
    }
  }

  @SneakyThrows
  @GetMapping("/{poiId}")
  public PoiDto getPoi(@PathVariable("poiId") String poiId) {
    return poiMapper.deepMap(poiRepository
            .findById(poiId)
            .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable)
    );
  }

  //endregion

  @Authenticated
  @PostMapping("/{poiId}/comment")
  public void addComment(
          @AuthenticationPrincipal UserPrincipal userPrincipal,
          @PathVariable("poiId") String poiId,
          @RequestBody RateCommentRequest request) {
    User user = userRepository
            .findById(userPrincipal.getId())
            .orElseThrow(ApiServerException.INTERNAL_SERVER_ERROR::getThrowable);
    contentManager.addComment(poiId, user, request);
  }

}
