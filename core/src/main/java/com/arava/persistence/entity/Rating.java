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
 * Date : 14/01/2020 08:07
 * All rights reserved.
 */


@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rating extends AbstractEntity {

  @Field
  @Column
  @NotBlank
  private Double score;

  @ManyToOne
  private Poi poi;

  @IndexedEmbedded
  @ManyToOne
  private User author;

}
