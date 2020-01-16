package com.arava.persistence.repository;

import com.arava.persistence.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:58
 * All rights reserved.
 */

public interface PoiRepository extends JpaRepository<Poi, String> {

}
