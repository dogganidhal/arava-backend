package com.arava.persistence.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
  private String title;

  @Column(length = 8192)
  @NotBlank
  private String description;

  @Transient
  public Language getLanguage() {
    return Language.fromCode(getLanguageCode());
  }

}
