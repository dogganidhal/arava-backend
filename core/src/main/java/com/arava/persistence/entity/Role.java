package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 20:26
 * All rights reserved.
 */

@Getter
@AllArgsConstructor
public enum Role {

  USER("user", 1),
  ADMIN("admin", 2);

  private String name;
  private Integer mask;

}