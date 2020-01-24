package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:45
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends AbstractEntity {

  @ManyToOne(cascade = CascadeType.ALL)
  private User author;

  @ManyToOne(cascade = CascadeType.ALL)
  private Poi poi;

  @Column
  private String content;

}
