package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Nidhal Dogga
 * Date : 24/01/2020 09:42
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocalizedResource extends AbstractEntity {

  @NotNull
  @Field(termVector = TermVector.YES)
  private String resource;

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  private Language language;

}
