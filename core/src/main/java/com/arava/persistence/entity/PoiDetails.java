package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import javax.persistence.*;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:42
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class PoiDetails extends AbstractEntity {

  @OneToOne(cascade = CascadeType.ALL)
  private Poi poi;

  @GenericField
  @Column
  private String address;

  @GenericField
  @Column
  private String phone;

  @GenericField
  @Column
  private String email;

  @Column(length = 1024)
  private String website;

  @Column(length = 1024)
  private String facebookUrl;

  @Column(length = 1024)
  private String instagramAccount;

  @Column
  private String openingHours;

}
