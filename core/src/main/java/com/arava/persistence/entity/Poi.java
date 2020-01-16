package com.arava.persistence.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Poi extends AbstractEntity {

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

  @NotBlank
  private Point coordinate;


  @Enumerated(EnumType.STRING)
  private Island island;

  private Boolean sponsored = false;

  private Boolean featured = false;

}
