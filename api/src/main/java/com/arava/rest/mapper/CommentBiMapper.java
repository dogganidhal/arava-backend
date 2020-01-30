package com.arava.rest.mapper;

import com.arava.persistence.entity.Comment;
import com.arava.persistence.entity.User;
import com.arava.rest.dto.PoiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 10:57
 * All rights reserved.
 */

@Component
public class CommentBiMapper implements Mapper<Comment, PoiDto.Comment>, ReverseMapper<Comment, PoiDto.Comment> {

  @Autowired
  private Mapper<User, PoiDto.User> userMapper;

  @Override
  public PoiDto.Comment deepMap(Comment object) {
    return PoiDto.Comment.builder()
            .id(object.getId())
            .content(object.getContent())
            .date(object.getCreated())
            .author(userMapper.deepMap(object.getAuthor()))
            .build();
  }

  @Override
  public Comment reverseMap(PoiDto.Comment object) {
    return null;
  }

}
