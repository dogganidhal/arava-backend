package com.arava.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class PoiTheme extends LocalizableEntity {

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
  @ColumnDefault(value = "FALSE")
  @Builder.Default
  private Boolean disabled = false;

  @AssociationInverseSide(inversePath = @ObjectPath(
          @PropertyValue(propertyName = "owner")
  ))
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<LocalizedResource<PoiTheme>> name;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media icon;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media marker;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media sponsoredMarker;

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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
  private List<PoiTheme> subThemes;

}
