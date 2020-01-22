package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:34
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiType extends AbstractEntity {

  @Field(termVector = TermVector.YES)
  @Column
  private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Media icon;

  @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn
  private List<PoiCategory> categories;

}