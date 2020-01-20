package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 20:26
 * All rights reserved.
 */

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity {

  @NotBlank
  @UniqueElements
  private String name;

  @OneToMany
  private List<Authority> authorities;

}