package com.arava.persistence.repository;

import com.arava.persistence.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 14/01/2020 06:58
 * All rights reserved.
 */

public interface PoiRepository extends JpaRepository<Poi, String> {

  @Override
  @Query("SELECT p FROM Poi p WHERE p.disabled = false")
  List<Poi> findAll();

  @Modifying
  @Transactional
  @Query("UPDATE Poi p SET p.draft = CASE p.draft WHEN TRUE THEN FALSE ELSE TRUE END WHERE p.id = ?1")
  void toggleDraft(String id);

}
