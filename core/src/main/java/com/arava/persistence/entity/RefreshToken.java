package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:58
 * All rights reserved.
 */


@Data
@SuperBuilder
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
  private LocalDateTime created;

  @UpdateTimestamp
  @Column
  private LocalDateTime updated;

  @Column
  @Builder.Default
  private Boolean revoked = false;

  @ManyToOne(cascade = CascadeType.ALL)
  private User user;

}
