package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
@SelectBeforeUpdate
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class Island extends AbstractEntity {

  @Field(termVector = TermVector.YES)
  private String name;

  private Double latitude;

  private Double longitude;

  private Double zoom;

  @IndexedEmbedded
  @ManyToOne
  private Archipelago archipelago;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media image;

}
