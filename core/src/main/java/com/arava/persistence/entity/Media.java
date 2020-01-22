package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 23:55
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Media extends AbstractEntity {

  @Column
  private String url;

  @Column
  private String mediaType;

}
