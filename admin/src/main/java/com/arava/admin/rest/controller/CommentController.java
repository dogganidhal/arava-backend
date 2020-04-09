package com.arava.admin.rest.controller;

import com.arava.admin.rest.dto.RatingDto;
import com.arava.business.manager.ContentManager;
import com.arava.persistence.entity.Rating;
import com.arava.persistence.repository.RatingRepository;
import com.arava.server.annotation.Admin;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 27/03/2020 22:05
 * All rights reserved.
 */


@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  private RatingRepository ratingRepository;

  @Autowired
  private Mapper<Rating, RatingDto> ratingMapper;

  @Autowired
  private ContentManager contentManager;

  @Admin
  @GetMapping("/pending")
  public List<RatingDto> listUnapprovedRatings() {
    return ratingRepository.findPendingRatings().stream()
            .map(ratingMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Admin
  @GetMapping("/archive")
  public List<RatingDto> listArchiveRatings() {
    return ratingRepository.findArchiveRatings().stream()
            .map(ratingMapper::deepMap)
            .collect(Collectors.toList());
  }

  @Admin
  @DeleteMapping("/{ratingId}")
  public void deleteRating(@PathVariable("ratingId") String commentId) {
    contentManager.deleteRating(commentId);
  }

  @Admin
  @PutMapping("/{ratingId}/approve")
  public void approveRating(@PathVariable("ratingId") String ratingId) {
    contentManager.approveRating(ratingId);
  }

  @Admin
  @PutMapping("/{ratingId}/decline")
  public void declineRating(@PathVariable("ratingId") String ratingId) {
    contentManager.declineRating(ratingId);
  }

}
