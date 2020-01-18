package com.arava.persistence.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Poi extends AbstractEntity {

  @OneToOne
  private PoiDetails details;

  @OneToMany
  private List<PoiLocalizedDescription> localizedDescriptions;

  @ManyToOne
  private PoiCategory category;

  @OneToMany
  private List<Rating> ratings;

  @OneToMany
  private List<Media> medias;

  @OneToMany
  private List<Comment> comments;

  @Column
  @NotBlank
  private Double latitude;

  @Column
  @NotBlank
  private Double longitude;

  @Column
  @NotBlank
  private String island;

  @Column
  @Builder.Default
  private Boolean sponsored = false;

  @Column
  @Builder.Default
  private Boolean featured = false;

}
