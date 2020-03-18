package com.arava.persistence.repository;

import com.arava.persistence.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 17/03/2020 14:36
 * All rights reserved.
 */

public interface RatingRepository extends JpaRepository<Rating, String> {

}
