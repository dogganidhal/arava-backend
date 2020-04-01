package com.arava.persistence.repository;

import com.arava.persistence.entity.PoiTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:15
 * All rights reserved.
 */

public interface PoiThemeRepository extends JpaRepository<PoiTheme, String> {

  @Override
  @Query("SELECT t FROM PoiTheme t WHERE t.disabled = FALSE")
  List<PoiTheme> findAll();

  @Query("SELECT DISTINCT t FROM PoiTheme t JOIN Poi p ON p.theme.id = t.id WHERE p.draft = FALSE")
  List<PoiTheme> findAllHavingPois();

}
