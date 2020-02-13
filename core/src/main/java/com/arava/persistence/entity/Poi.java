package com.arava.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.*;
import org.hibernate.search.bridge.builtin.BooleanBridge;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@SuperBuilder
@Indexed
@Spatial(spatialMode = SpatialMode.HASH)
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Poi {

  @Id
  @SortableField
  @DocumentId
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
  private List<LocalizedResource> title;

  @ContainedIn
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<LocalizedResource> description;

  @ContainedIn
  @IndexedEmbedded
  @ManyToOne(cascade = CascadeType.ALL)
  private PoiTheme theme;

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
  private Island island;

}
