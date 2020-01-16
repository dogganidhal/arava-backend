package com.arava.persistence.entity;


import lombok.*;
import org.springframework.data.geo.Point;

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

  @Column
  @NotBlank
  private String title;

  @OneToOne
  private PoiDetails details;

  @OneToMany
  private List<PoiLocalizedDescription> localizedDescriptions;

  @ManyToOne
  private PoiCategory category;

  @OneToMany
  private List<Rating> ratings;

  @OneToMany
  private List<PoiMedia> medias;

  @Column
  @NotBlank
  private Point coordinate;

  @Column
  @Enumerated(EnumType.STRING)
  private Island island;

  @Column
  @Builder.Default
  private Boolean sponsored = false;

  @Column
  @Builder.Default
  private Boolean featured = false;

}
