package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

  @ManyToOne
  @IndexedEmbedded
  private Language language;

  @Column
  @NotBlank
  @SortableField
  @Field(termVector = TermVector.YES)
  private String title;

  @NotBlank
  @Column(length = 8192)
  @Field(termVector = TermVector.YES)
  private String description;

}
