package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 22:53
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiLocalizedDescription extends AbstractEntity {

  @ManyToOne
  private Poi poi;

  @Column
  @NotBlank
  private String languageCode;

  @Column
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
