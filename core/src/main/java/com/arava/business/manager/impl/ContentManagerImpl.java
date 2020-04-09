package com.arava.business.manager.impl;

import com.arava.business.manager.ContentManager;
import com.arava.persistence.entity.*;
import com.arava.persistence.repository.PoiRepository;
import com.arava.persistence.repository.RatingRepository;
import com.arava.server.dto.request.RateCommentRequest;
import com.arava.server.exception.ApiClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 17/03/2020 14:22
 * All rights reserved.
 */

@Component
public class ContentManagerImpl implements ContentManager {

  @Autowired
  private PoiRepository poiRepository;

  @Autowired
  private RatingRepository ratingRepository;

  @Override
  public void addRating(String poiId, User user, RateCommentRequest request) {
    Poi poi = poiRepository
            .findById(poiId)
            .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable);
    Rating rating = Rating.builder()
            .author(user)
            .poi(poi)
            .score(request.getRating())
            .comment(request.getComment())
            .build();
    ratingRepository.save(rating);
  }

  @Override
  public void approveRating(String commentId) {
    Rating comment = ratingRepository
            .findById(commentId)
            .orElseThrow(ApiClientException.COMMENT_NOT_FOUND::getThrowable);
    comment.setStatus(RatingStatus.APPROVED);
    ratingRepository.save(comment);
    // TODO: Insert any logic after approving the comment, ex : notify the user
  }

  @Override
  public void declineRating(String commentId) {
    Rating comment = ratingRepository
            .findById(commentId)
            .orElseThrow(ApiClientException.COMMENT_NOT_FOUND::getThrowable);
    comment.setStatus(RatingStatus.DECLINED);
    ratingRepository.save(comment);
    // TODO: Insert any logic after refusing the comment, ex : notify the user
  }

  @Override
  public void deleteRating(String commentId) {
    Rating comment = ratingRepository
            .findById(commentId)
            .orElseThrow(ApiClientException.COMMENT_NOT_FOUND::getThrowable);
    comment.setDisabled(true);
    ratingRepository.save(comment);
  }

}
