package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 20:26
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@Indexed
@Cacheable
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class Archipelago extends AbstractEntity {

  private String name;

  @OneToMany(mappedBy = "archipelago")
  private Collection<Island> islands;

}
