package com.arava.persistence.entity;


import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Poi extends AbstractEntity {

  @IndexedEmbedded
  @OneToOne
  private PoiDetails details;

  @IndexedEmbedded
  @OneToMany
  private List<PoiLocalizedDescription> localizedDescriptions;

  @IndexedEmbedded
  @ManyToOne
  private PoiCategory category;

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
  private Double latitude;

  @Field
  @Column
  @NotBlank
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
