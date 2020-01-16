package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;

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

  @Column
  private String website;

  @Column
  private String facebookUrl;

  @Column
  private String instagramAccount;

  @Column
  private Date openingHour;

  @Column
  private Date closingHour;

}
