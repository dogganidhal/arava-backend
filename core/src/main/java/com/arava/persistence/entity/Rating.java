package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 08:07
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Rating extends AbstractEntity {

  @Column
  @NotBlank
  private Integer score;

  @ManyToOne
  private Poi poi;

}
