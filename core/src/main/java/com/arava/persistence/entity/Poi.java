package com.arava.persistence.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.GeoPointBinding;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.Latitude;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.Longitude;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@SuperBuilder
@Indexed(index = "com.arava.poi")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@GeoPointBinding(fieldName = "coordinate")
@EqualsAndHashCode(callSuper = true)
public class Poi extends LocalizableEntity {

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
  @GenericField
  @ColumnDefault(value = "FALSE")
  @Builder.Default
  private Boolean disabled = false;

  /**
   * Premium poi (paid subscription)
   */

  @GenericField
  @Column
  @Builder.Default
  private Boolean sponsored = false;

  /**
   * Poi to be displayed in photos tab
   */

  @GenericField
  @Column
  @Builder.Default
  private Boolean featured = false;

  /**
   * Whether the poi is in draft or published state
   */

  @GenericField
  @Column
  @Builder.Default
  private Boolean draft = false;

  @AssociationInverseSide(inversePath = @ObjectPath(
          @PropertyValue(propertyName = "poi")
  ))
  @IndexedEmbedded
  @OneToOne(cascade = CascadeType.ALL)
  private PoiDetails details;

  @AssociationInverseSide(inversePath = @ObjectPath(
          @PropertyValue(propertyName = "owner")
  ))
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<LocalizedResource<Poi>> title;

  @AssociationInverseSide(inversePath = @ObjectPath(
          @PropertyValue(propertyName = "owner")
  ))
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<LocalizedResource<Poi>> description;

  @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  private PoiTheme theme;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  private Media mainImage;

  @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<Rating> ratings;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Media> medias;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Comment> comments;

  @GenericField
  @Column
  @Latitude
  private Double latitude;

  @GenericField
  @Column
  @Longitude
  private Double longitude;

  @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  private Island island;

}
