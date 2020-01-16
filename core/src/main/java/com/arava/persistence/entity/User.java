package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:50
 * All rights reserved.
 */


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="\"user\"")
public class User extends AbstractEntity {

  @Column
  private String firstName;

  @Column
  private String lastName;

  @NotBlank
  @Column
  private String email;

  @Column
  private String passwordHash;

  @Column
  @Builder.Default
  private Integer roleMask = 1;

  @Column
  @Builder.Default
  private Boolean expired = false;

  @Column
  @Builder.Default
  private Boolean locked = false;

  @OneToMany
  private List<Favorite> favorites;

  @ManyToOne
  private Media avatar;

  @Transient
  private List<Role> roles;

  @PrePersist
  @PostLoad
  private void fillRoles() {
    roles = Arrays.stream(Role.values())
            .filter(role -> (role.getMask() & roleMask) != 0)
            .collect(Collectors.toList());
  }

}
