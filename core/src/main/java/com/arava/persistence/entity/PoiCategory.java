package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:30
 * All rights reserved.
 */


@Data
@Entity
@Indexed
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiCategory extends AbstractEntity {

  @Field(termVector = TermVector.YES)
  @Column
  private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Media icon;

  @IndexedEmbedded(depth = 2)
  @ManyToOne(cascade = CascadeType.ALL)
  @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  @JoinColumn
  private PoiType type;

}
