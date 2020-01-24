package com.arava.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:50
 * All rights reserved.
 */


@Data
@SuperBuilder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="\"user\"")
public class User extends AbstractEntity {

  @Field
  @Column
  private String firstName;

  @Field
  @Column
  private String lastName;

  @Field
  @Column
  private String email;

  @Column
  private String passwordHash;

  @Column
  @Builder.Default
  private Boolean expired = false;

  @Column
  @Builder.Default
  private Boolean locked = false;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Favorite> favorites;

  @ManyToOne(cascade = CascadeType.ALL)
  private Media avatar;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @Builder.Default
  private List<Role> roles = new ArrayList<>();

}
