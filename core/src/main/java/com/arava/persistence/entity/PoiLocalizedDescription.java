package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 22:53
 * All rights reserved.
 */


@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiLocalizedDescription extends AbstractEntity {

  @ManyToOne
  private Poi poi;

  @Field
  @Column
  @NotBlank
  private String languageCode;

  @Field
  @Column
  @NotBlank
  private String title;

  @Field
  @Column(length = 8192)
  @NotBlank
  private String description;

  @Transient
  public Language getLanguage() {
    return Language.fromCode(getLanguageCode());
  }

}
