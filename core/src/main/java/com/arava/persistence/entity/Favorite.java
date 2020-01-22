package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:48
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Favorite extends AbstractEntity {

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private User user;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Poi poi;

}
