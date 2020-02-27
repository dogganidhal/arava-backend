package com.arava.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Nidhal Dogga
 * Date : 27/02/2020 07:26
 * All rights reserved.
 */

@Entity
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LocalizableEntity extends AbstractEntity {

}
