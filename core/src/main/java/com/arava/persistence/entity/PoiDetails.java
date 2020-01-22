package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Time;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:42
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiDetails extends AbstractEntity {

  @OneToOne(cascade = CascadeType.ALL)
  private Poi poi;

  @Field
  @Column
  private String address;

  @Field
  @Column
  private String phone;

  @Field
  @Column
  private String email;

  @Column(length = 1024)
  private String website;

  @Column(length = 1024)
  private String facebookUrl;

  @Column(length = 1024)
  private String instagramAccount;

  @Field
  @Column
  private Time openingHour;

  @Field
  @Column
  private Time closingHour;

}
