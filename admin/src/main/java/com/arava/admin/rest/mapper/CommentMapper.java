package com.arava.admin.rest.mapper;

import com.arava.admin.rest.dto.RatingDto;
import com.arava.admin.rest.dto.PoiDto;
import com.arava.admin.rest.dto.UserDto;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.Rating;
import com.arava.persistence.entity.User;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 27/03/2020 21:33
 * All rights reserved.
 */

@Component
public class CommentMapper implements Mapper<Rating, RatingDto> {

  @Autowired
  private Mapper<User, UserDto> userMapper;

  @Autowired
  private Mapper<Poi, PoiDto> poiMapper;

  @Override
  public RatingDto deepMap(Rating object) {
    return RatingDto.builder()
            .id(object.getId())
            .author(userMapper.deepMap(object.getAuthor()))
            .comment(object.getComment())
            .score(object.getScore())
            .dateTime(object.getCreated())
            .poi(poiMapper.partialMap(object.getPoi())) // Avoid overflow
            .status(object.getStatus() != null ?
                    RatingDto.Status.values()[object.getStatus().ordinal()] :
                    RatingDto.Status.UNKNOWN
            )
            .build();
  }

}
