package com.arava.rest.mapper;

import com.arava.persistence.entity.User;
import com.arava.rest.dto.UserDto;
import com.arava.server.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 29/02/2020 19:36
 * All rights reserved.
 */

@Component
public class UserMapper implements Mapper<User, UserDto> {

  @Override
  public UserDto deepMap(User object) {
    return UserDto.builder()
            .id(object.getId())
            .firstName(object.getFirstName())
            .lastName(object.getLastName())
            .email(object.getEmail())
            .build();
  }

}
