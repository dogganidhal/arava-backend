package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:42
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiDetails extends AbstractEntity {

  @OneToOne
  private Poi poi;

  @Column
  private String address;

  @Column
  private String phone;

  @Column
  private String email;

  @Column(length = 1024)
  private String website;

  @Column(length = 1024)
  private String facebookUrl;

  @Column(length = 1024)
  private String instagramAccount;

  @Column
  private Time openingHour;

  @Column
  private Time closingHour;

}
