package com.arava.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 22:53
 * All rights reserved.
 */


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PoiLocalizedDescription extends AbstractEntity {

  @ManyToOne
  private Poi poi;

  @NotBlank
  private String languageCode;

  @NotBlank
  private String description;

  @Transient
  private Language language;

  @PostLoad
  private void fillLanguage() {
    if (languageCode != null) {
      language = Language.fromCode(languageCode);
    }
  }

  @PrePersist
  private void fillLanguageCode() {
    if (language != null) {
      languageCode = language.getCode();
    }
  }

}
