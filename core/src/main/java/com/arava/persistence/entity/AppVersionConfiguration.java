package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 22/01/2020 07:37
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppVersionConfiguration {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @CreationTimestamp
  @Column
  private LocalDateTime created;

  @NotNull
  @Column
  private Integer minVersion;

  @Column
  private LocalDateTime maxDate;

  @Builder.Default
  @Column
  private Boolean forceUpdate = false;

}
