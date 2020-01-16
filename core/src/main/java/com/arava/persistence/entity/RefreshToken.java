package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:58
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private String token;

  @CreationTimestamp
  @Column
  private Date created;

  @UpdateTimestamp
  @Column
  private Date updated;

  @Column
  @Builder.Default
  private Boolean revoked = false;

  @ManyToOne
  private User user;

}
