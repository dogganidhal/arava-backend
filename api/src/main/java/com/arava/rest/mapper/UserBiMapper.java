package com.arava.rest.mapper;

import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.User;
import com.arava.rest.dto.MediaDto;
import com.arava.rest.dto.PoiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:00
 * All rights reserved.
 */


@Component
public class UserBiMapper implements Mapper<User, PoiDto.User>, ReverseMapper<User, PoiDto.User> {

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Override
  public PoiDto.User deepMap(User object) {
    return PoiDto.User.builder()
            .id(object.getId())
            .fullName(object.getFirstName() + " " + object.getLastName())
            .avatar(mediaMapper.deepMap(object.getAvatar()))
            .build();
  }

  @Override
  public User reverseMap(PoiDto.User object) {
    return null;
  }

}
