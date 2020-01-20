package com.arava.persistence.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.Entity;


/**
 *  Created by Nidhal Dogga
 *  Date : 14/01/2020 22:53
 *  All rights reserved.
 */

@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Language extends AbstractEntity {

  @Field(termVector = TermVector.YES)
  private String code;

  @Field(termVector = TermVector.YES)
  private String name;

}
