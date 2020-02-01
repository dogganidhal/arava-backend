package com.arava.rest.controller;

import com.arava.persistence.entity.Island;
import com.arava.persistence.merger.EntityMerger;
import com.arava.persistence.repository.IslandRepository;
import com.arava.rest.annotation.Admin;
import com.arava.rest.dto.IslandDto;
import com.arava.rest.dto.request.IslandUpdateRequest;
import com.arava.rest.exception.ApiClientException;
import com.arava.rest.mapper.Mapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:29
 * All rights reserved.
 */


@RestController
public class IslandController {

  @Autowired
  private IslandRepository islandRepository;

  @Autowired
  private Mapper<Island, IslandDto> islandMapper;

  @Autowired
  private Mapper<IslandUpdateRequest, Island> islandUpdateMapper;

  @Autowired
  private EntityMerger<Island> islandMerger;

  //region Island inspection

  @GetMapping("/island")
  public List<IslandDto> getIslands() {
    return islandRepository.findAll().stream()
            .map(islandMapper::deepMap)
            .collect(Collectors.toList());
  }

  @SneakyThrows
  @Admin
  @PutMapping("/island")
  public void updateIsland(@Valid @RequestBody IslandUpdateRequest request) {
    try {
      Island island = islandMerger.merge(
              islandRepository
                      .findById(request.getId())
                      .orElseThrow(ApiClientException.NOT_FOUND::getThrowable),
              islandUpdateMapper.deepMap(request)
      );
      islandRepository.save(island);
    } catch (ConstraintViolationException e) {
      throw ApiClientException.VALIDATION_ERROR
              .getThrowable();
    }
  }

  //endregion

}
