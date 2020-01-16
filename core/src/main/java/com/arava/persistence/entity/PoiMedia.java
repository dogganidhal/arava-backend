package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:48
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiMedia extends AbstractEntity {

  @ManyToOne
  private Poi poi;

  @Column
  @NotBlank
  private String url;

  @Column
  @NotBlank
  private String mediaType;

}
