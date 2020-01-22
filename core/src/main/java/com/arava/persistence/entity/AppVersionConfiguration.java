package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
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
import java.time.LocalDate;

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
  private LocalDate created;

  @NotNull
  private Integer minVersion;

  @NotNull
  private LocalDate maxDate;

  private Boolean forceUpdate;

}
