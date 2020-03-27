package com.arava.admin.rest.mapper;

import com.arava.admin.rest.dto.UserDto;
import com.arava.persistence.entity.User;
import com.arava.server.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 26/03/2020 18:38
 * All rights reserved.
 */

@Component
public class UserMapper implements Mapper<User, UserDto> {

  @Override
  public UserDto deepMap(User object) {
    return UserDto.builder()
            .id(object.getId())
            .email(object.getEmail())
            .firstName(object.getFirstName())
            .lastName(object.getLastName())
            .build();
  }

}
