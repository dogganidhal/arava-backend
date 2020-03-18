package com.arava.business.manager.impl;

import com.arava.business.manager.ContentManager;
import com.arava.persistence.entity.Comment;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.Rating;
import com.arava.persistence.entity.User;
import com.arava.persistence.repository.CommentRepository;
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
  private CommentRepository commentRepository;

  @Autowired
  private RatingRepository ratingRepository;

  @Override
  public void addComment(String poiId, User user, RateCommentRequest request) {
    Poi poi = poiRepository
            .findById(poiId)
            .orElseThrow(ApiClientException.POI_NOT_FOUND::getThrowable);
    Rating rating = Rating.builder()
            .author(user)
            .poi(poi)
            .score(request.getRating())
            .build();
    Comment comment = Comment.builder()
            .author(user)
            .poi(poi)
            .content(request.getComment())
            .build();
    commentRepository.save(comment);
    ratingRepository.save(rating);
  }

}
