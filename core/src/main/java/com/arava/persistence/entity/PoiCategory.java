package com.arava.persistence.entity;

import lombok.*;

import javax.persistence.Entity;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 07:30
 * All rights reserved.
 */


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PoiCategory extends PoiCategorySection {

}
