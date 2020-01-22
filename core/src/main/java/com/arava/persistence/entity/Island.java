package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

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

  private String name;

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Archipelago archipelago;

}
