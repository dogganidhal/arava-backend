package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:48
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Favorite extends AbstractEntity {

  @ManyToOne
  private User user;

  @ManyToOne
  private Poi poi;

}
