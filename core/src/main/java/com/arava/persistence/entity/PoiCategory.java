package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:30
 * All rights reserved.
 */


@Data
@Entity
@Indexed
@EqualsAndHashCode(callSuper = true)
public class PoiCategory extends AbstractEntity {

  @Field
  @Column
  @NotBlank
  private String name;

  @ManyToOne
  private Media icon;

  @IndexedEmbedded
  @ManyToOne
  private PoiType type;

}
