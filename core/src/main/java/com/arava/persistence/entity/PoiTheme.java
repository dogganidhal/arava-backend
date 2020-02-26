package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:30
 * All rights reserved.
 */


@Data
@Entity
@Embeddable
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class PoiTheme extends AbstractEntity {

  @IndexingDependency
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<LocalizedResource> name;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media icon;

  @IndexingDependency
  @IndexedEmbedded(maxDepth = 3)
  @ManyToOne(cascade = CascadeType.ALL)
  @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  private PoiTheme parent;

  @OneToMany(cascade = CascadeType.ALL)
  private List<PoiTheme> subThemes;

}
