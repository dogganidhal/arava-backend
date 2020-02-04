package com.arava.server.context;

import com.arava.persistence.entity.Language;
import com.arava.persistence.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Created by Nidhal Dogga
 * Date : 31/01/2020 18:56
 * All rights reserved.
 */

@Component
@RequestScope
public class ContextResolver {

  private Language language;

  @Autowired
  private LanguageRepository languageRepository;

  public void setLanguageCode(String languageCode) {
    language = languageRepository
            .findById(languageCode)
            .orElse(null);
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public Language getLanguage() {
    return language != null ?
            language :
            languageRepository
                    .findById("fr")
                    .get();
  }

}
