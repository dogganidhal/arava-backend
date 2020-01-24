package com.arava.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:30
 * All rights reserved.
 */


@Data
@Entity
@Indexed
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PoiCategory extends AbstractEntity {

  @ContainedIn
  @IndexedEmbedded
  @OneToMany(cascade = CascadeType.ALL)
  private List<LocalizedResource> name;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media icon;

  @IndexedEmbedded(depth = 2)
  @ManyToOne(cascade = CascadeType.ALL)
  @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
  private PoiType type;

}
