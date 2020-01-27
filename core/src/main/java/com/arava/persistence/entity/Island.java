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

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 21:59
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@Indexed
@Cacheable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Island extends AbstractEntity {

  @NotNull
  @Field(termVector = TermVector.YES)
  private String name;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotNull
  private Double zoom;

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  private Archipelago archipelago;

}
