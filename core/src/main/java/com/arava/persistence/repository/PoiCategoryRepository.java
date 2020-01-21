package com.arava.persistence.repository;

import com.arava.persistence.entity.PoiCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:15
 * All rights reserved.
 */

public interface PoiCategoryRepository extends JpaRepository<PoiCategory, String> {

}