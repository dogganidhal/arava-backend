package com.arava.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 23:42
 * All rights reserved.
 */


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PoiDetails extends AbstractEntity {

  @OneToOne
  private Poi poi;

  private String address;
  private String phone;
  private String email;
  private String website;
  private String facebookUrl;
  private String instagramAccount;
  private Date openingHour;
  private Date closingHour;

}
