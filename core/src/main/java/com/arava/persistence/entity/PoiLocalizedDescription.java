package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.annotations.*;

import javax.persistence.*;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 22:53
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiLocalizedDescription extends AbstractEntity {

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Poi poi;

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Language language;

  @Column
  @SortableField
  @Field(termVector = TermVector.YES)
  private String title;

  @Column(length = 8192)
  @Field(termVector = TermVector.YES)
  private String description;

}
