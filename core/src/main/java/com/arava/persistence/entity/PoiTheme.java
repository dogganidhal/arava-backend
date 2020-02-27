package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.AssociationInverseSide;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.ObjectPath;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.PropertyValue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:30
 * All rights reserved.
 */


@Data
@Entity
@Embeddable
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class PoiTheme extends LocalizableEntity {

  @Id
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
  @ColumnDefault(value = "FALSE")
  private Boolean disabled = false;

  @AssociationInverseSide(inversePath = @ObjectPath(
          @PropertyValue(propertyName = "owner")
  ))
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<LocalizedResource<PoiTheme>> name;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media icon;

  @AssociationInverseSide(inversePath = @ObjectPath(
          @PropertyValue(propertyName = "subThemes")
  ))
  @IndexedEmbedded(maxDepth = 3)
  @ManyToOne(cascade = CascadeType.ALL)
  @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  private PoiTheme parent;

  @AssociationInverseSide(inversePath = @ObjectPath(
          @PropertyValue(propertyName = "parent")
  ))
  @OneToMany(cascade = CascadeType.ALL)
  private List<PoiTheme> subThemes;

}
