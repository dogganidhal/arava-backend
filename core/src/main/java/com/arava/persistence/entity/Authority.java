package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 21:57
 * All rights reserved.
 */


@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Authority extends AbstractEntity {

  private String name;

}
