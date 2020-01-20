package com.arava.persistence.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;


@Data
@Builder
@Entity
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
  private Boolean disabled = false;

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

  @Field(termVector = TermVector.YES)
  @Column
  @NotBlank
  private String island;

  @Field
  @Column
  @Builder.Default
  private Boolean sponsored = false;

  @Field
  @Column
  @Builder.Default
  private Boolean featured = false;

}
