package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 20:26
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity {

  @UniqueElements
  private String name;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Authority> authorities;

}