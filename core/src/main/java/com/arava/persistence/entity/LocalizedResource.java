package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.*;

/**
 * Created by Nidhal Dogga
 * Date : 24/01/2020 09:42
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocalizedResource<T extends AbstractEntity> extends AbstractEntity {

  @KeywordField
  @Column(length = 16384)
  private String resource;

  @ManyToOne(cascade = CascadeType.ALL)
  private Language language;

  @ManyToOne(targetEntity = LocalizableEntity.class)
  private T owner;

}
