package com.arava.persistence.repository;

import com.arava.persistence.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 17/03/2020 14:36
 * All rights reserved.
 */

public interface RatingRepository extends JpaRepository<Rating, String> {

  @Query("SELECT r FROM Rating r WHERE r.status = 'UNKNOWN' OR r.status IS NULL AND r.disabled = FALSE")
  List<Rating> findPendingRatings();

  @Query("SELECT c FROM Rating c WHERE c.status = 'APPROVED' AND c.disabled = FALSE")
  List<Rating> findArchiveRatings();

}
