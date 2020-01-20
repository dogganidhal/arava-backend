package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 21:59
 * All rights reserved.
 */

@Data
@Builder
@Entity
@Indexed
@Cacheable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Island extends AbstractEntity {

  @NotBlank
  private String name;

}
