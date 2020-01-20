package com.arava.persistence.repository;

import com.arava.persistence.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:23
 * All rights reserved.
 */

public interface LanguageRepository extends JpaRepository<Language, String> {

}
