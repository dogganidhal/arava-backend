package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 21:59
 * All rights reserved.
 */

@Data
@SuperBuilder
@Entity
@Cacheable
@AllArgsConstructor
@NoArgsConstructor
@SelectBeforeUpdate
@DynamicUpdate
public class Island {

  @Id
  @GenericField
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @CreationTimestamp
  @Column
  private LocalDateTime created;

  @UpdateTimestamp
  @Column
  private LocalDateTime updated;

  @Column
  private Boolean disabled = false;

  @GenericField
  private String name;

  private Double latitude;

  private Double longitude;

  private Double zoom;

  @IndexedEmbedded
  @ManyToOne
  private Archipelago archipelago;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media image;

}
