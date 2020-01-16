package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 23:55
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Media extends AbstractEntity {

  @Column
  @NotBlank
  private String url;

  @Column
  @NotBlank
  private String mediaType;

}
