package com.arava.rest.mapper;

import com.arava.persistence.entity.Rating;
import com.arava.persistence.entity.User;
import com.arava.rest.dto.CommentAuthorDto;
import com.arava.rest.dto.CommentDto;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 10:57
 * All rights reserved.
 */

@Component
public class CommentMapper implements Mapper<Rating, CommentDto> {

  @Autowired
  private Mapper<User, CommentAuthorDto> userMapper;

  @Override
  public CommentDto deepMap(Rating object) {
    return CommentDto.builder()
            .id(object.getId())
            .content(object.getComment())
            .score(object.getScore())
            .date(object.getCreated())
            .author(userMapper.deepMap(object.getAuthor()))
            .build();
  }

}
