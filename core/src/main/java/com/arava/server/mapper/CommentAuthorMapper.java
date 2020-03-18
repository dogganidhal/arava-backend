package com.arava.server.mapper;

import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.User;
import com.arava.server.dto.CommentAuthorDto;
import com.arava.server.dto.MediaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 08:09
 * All rights reserved.
 */

@Component
public class CommentAuthorMapper implements Mapper<User, CommentAuthorDto> {

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Override
  public CommentAuthorDto deepMap(User object) {
    return CommentAuthorDto.builder()
            .id(object.getId())
            .fullName(String.format("%s %s", object.getFirstName(), object.getLastName()))
            .avatar(object.getAvatar() != null ?
                    mediaMapper.deepMap(object.getAvatar()) :
                    null
            )
            .build();
  }

}
