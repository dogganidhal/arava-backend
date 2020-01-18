package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:30
 * All rights reserved.
 */


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PoiCategory extends AbstractEntity {

  @Column
  @NotBlank
  private String name;

  @ManyToOne
  private Media icon;

  @ManyToOne
  private PoiType type;

}
