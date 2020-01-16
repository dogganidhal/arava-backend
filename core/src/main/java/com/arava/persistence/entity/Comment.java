package com.arava.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:45
 * All rights reserved.
 */


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Comment extends AbstractEntity {

  @ManyToOne
  private User user;

  @NotBlank
  private String content;


}
