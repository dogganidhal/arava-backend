package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:34
 * All rights reserved.
 */

@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiType extends AbstractEntity {

  @Field
  @Column
  @NotBlank
  private String name;

  @ManyToOne
  private Media icon;

  @OneToMany
  private List<PoiCategory> categories;

}