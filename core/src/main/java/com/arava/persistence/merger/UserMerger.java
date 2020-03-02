package com.arava.persistence.merger;

import com.arava.persistence.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 02/03/2020 10:38
 * All rights reserved.
 */

@Component
public class UserMerger implements EntityMerger<User> {

  @Override
  public User merge(User src, User dest) {
    return User.builder()
            .id(pick(src.getId(), dest.getId()))
            .firstName(pick(src.getFirstName(), dest.getFirstName()))
            .lastName(pick(src.getLastName(), dest.getLastName()))
            .email(pick(src.getEmail(), dest.getEmail()))
            .passwordHash(pick(src.getPasswordHash(), dest.getPasswordHash()))
            .build();
  }

}
