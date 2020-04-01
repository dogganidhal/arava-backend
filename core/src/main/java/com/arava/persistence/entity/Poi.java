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
  @GenericField
  @Getter
  @ColumnDefault(value = "FALSE")
  @Builder.Default
  private boolean disabled = false;

  /**
   * "À la une" poi (paid subscription level 1)
   */

  @Column
  @Getter
  @ColumnDefault(value = "FALSE")
  @Builder.Default
  private boolean sponsored = false;

  /**
   * "Activité" poi (paid subscription level 2)
   */

  @Column
  @Getter
  @ColumnDefault(value = "FALSE")
  @Builder.Default
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

  @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  private PoiTheme theme;

  @ManyToOne(cascade = CascadeType.ALL)
  private User owner;

  @OneToOne(cascade = CascadeType.ALL)
  private Media mainImage;

  @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.NO)
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "poi")
  private List<Rating> ratings;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Media> medias;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "poi")
  private List<Comment> comments;

  @GenericField
  @Column
  @Latitude
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
