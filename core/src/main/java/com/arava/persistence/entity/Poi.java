package com.arava.persistence.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.GeoPointBinding;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.Latitude;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.Longitude;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
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
  @DocumentId
  @NaturalId
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column
  @CreationTimestamp
  private LocalDateTime created;

  @Column
  @UpdateTimestamp
  private LocalDateTime updated;

  @Column
  @Getter
  @GenericField
  @Builder.Default
  @ColumnDefault(value = "FALSE")
  private boolean disabled = false;

  @Column
  @Getter
  @GenericField
  @Builder.Default
  @ColumnDefault(value = "TRUE")
  private boolean light = true;

  /**
   * "À la une" poi (paid subscription level 1)
   */

  @Column
  @Getter
  @Builder.Default
  @ColumnDefault(value = "FALSE")
  private boolean sponsored = false;

  /**
     * Temporary premium tagging
   */

  @Column
  @Getter
  @GenericField
  @Builder.Default
  @ColumnDefault(value = "FALSE")
  private boolean premium = false;

  /**
     * Default temporary premium tagging (returned when no theme is specified)
   */

  @Column
  @Getter
  @GenericField
  @Builder.Default
  @ColumnDefault(value = "FALSE")
  private boolean defaultPremium = false;

  /**
   * "Activité" poi (paid subscription level 2)
   */

  @Column
  @Getter
  @Builder.Default
  @ColumnDefault(value = "FALSE")
  private boolean activity = false;

  /**
   * Poi marked "choses à faire"
   */

  @Column
  @GenericField
  @Getter
  @ColumnDefault(value = "FALSE")
  @Builder.Default
  private boolean featured = false;

  /**
   * Whether the poi is in draft or published state
   */

  @Column
  @GenericField
  @Getter
  @ColumnDefault(value = "TRUE")
  @Builder.Default
  private boolean draft = true;

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

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
  private PoiTheme theme;

  @ManyToOne(cascade = CascadeType.ALL)
  private User owner;

  @OneToOne(cascade = CascadeType.ALL)
  private Media mainImage;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "poi")
  private List<Rating> ratings;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Media> medias;

  @Column
  @Latitude
  @GenericField
  private Double latitude;

  @Column
  @Longitude
  @GenericField
  private Double longitude;

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
  private Island island;

}
