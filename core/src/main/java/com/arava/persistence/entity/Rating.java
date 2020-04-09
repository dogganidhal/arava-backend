package com.arava.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import javax.persistence.*;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 08:07
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rating extends AbstractEntity {

  @Column
  @GenericField
  private Double score;

  @ManyToOne(cascade = CascadeType.ALL)
  private Poi poi;

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  private User author;

  @Column(length = 16384)
  private String comment;

  @Column
  @Builder.Default
  @Enumerated(EnumType.STRING)
  private RatingStatus status = RatingStatus.UNKNOWN;

}
