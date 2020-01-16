package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:34
 * All rights reserved.
 */

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiCategorySection extends AbstractEntity {

  @Column
  @NotBlank
  private String name;

  @ManyToOne
  private PoiMedia icon;

}