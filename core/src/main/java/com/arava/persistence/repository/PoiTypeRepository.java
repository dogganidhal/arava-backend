package com.arava.persistence.repository;

import com.arava.persistence.entity.PoiType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 08:32
 * All rights reserved.
 */

public interface PoiTypeRepository extends JpaRepository<PoiType, String> {

}
