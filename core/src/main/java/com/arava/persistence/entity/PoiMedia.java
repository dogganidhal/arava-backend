package com.arava.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:48
 * All rights reserved.
 */


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PoiMedia extends AbstractEntity {

  @ManyToOne
  private Poi poi;

  @NotBlank
  private String url;

  @NotBlank
  private String mediaType;

}
