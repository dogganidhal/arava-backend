package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 *  Created by Nidhal Dogga
 *  Date : 14/01/2020 22:53
 *  All rights reserved.
 */

@Getter
@AllArgsConstructor
public enum Language {

  FRENCH("fr", "French"),
  ENGLISH("en", "English"),
  CHINESE("cn", "Chinese");

  private String code;
  private String name;

  public static Language fromCode(String languageCode) {
    for (Language language : values()) {
      if (language.getCode().equals(languageCode)) {
        return language;
      }
    }
    return null;
  }

}
