package com.arava.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.*;
import org.hibernate.search.bridge.builtin.BooleanBridge;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@SuperBuilder
@Indexed
@Spatial(spatialMode = SpatialMode.HASH)
@AllArgsConstructor
@NoArgsConstructor
public class Poi {

  @Id
  @SortableField
  @DocumentId
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @CreationTimestamp
  @Column
  private LocalDate created;

  @UpdateTimestamp
  @Column
  private LocalDate updated;

  @Column
  @Builder.Default
  @Field
  @FieldBridge(impl = BooleanBridge.class)
  private Boolean disabled = false;

  /**
   * Will be the default search result filter
   */

  @Column
  @Builder.Default
  @Field
  @FieldBridge(impl = BooleanBridge.class)
  private Boolean thingsToDo = false;

  /**
   * Premium poi (paid subscription)
   */

  @Field
  @Column
  @Builder.Default
  private Boolean sponsored = false;

  /**
   * Poi to be displayed in photos tab
   */

  @Field
  @Column
  @Builder.Default
  private Boolean featured = false;

  @IndexedEmbedded
  @OneToOne(cascade = CascadeType.ALL)
  private PoiDetails details;

  @ContainedIn
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<PoiLocalizedDescription> localizedDescriptions;

  @ContainedIn
  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private PoiCategory category;

  @ContainedIn
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<Rating> ratings;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Media> medias;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Comment> comments;

  @Field
  @Column
  @Latitude
  private Double latitude;

  @Field
  @Column
  @Longitude
  private Double longitude;

  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Island island;

}
