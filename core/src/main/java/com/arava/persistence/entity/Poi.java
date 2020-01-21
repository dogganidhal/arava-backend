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
import javax.validation.constraints.NotBlank;
import java.sql.Date;
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
  private Date created;

  @UpdateTimestamp
  @Column
  private Date updated;

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
  @OneToOne
  private PoiDetails details;

  @ContainedIn
  @IndexedEmbedded
  @OneToMany
  private List<PoiLocalizedDescription> localizedDescriptions;

  @ContainedIn
  @IndexedEmbedded
  @ManyToOne
  private PoiCategory category;

  @ContainedIn
  @IndexedEmbedded
  @OneToMany
  private List<Rating> ratings;

  @OneToMany
  private List<Media> medias;

  @OneToMany
  private List<Comment> comments;

  @Field
  @Column
  @NotBlank
  @Latitude
  private Double latitude;

  @Field
  @Column
  @NotBlank
  @Longitude
  private Double longitude;

  @ManyToOne
  @IndexedEmbedded
  private Island island;

}
