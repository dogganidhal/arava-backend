package com.arava.server.mapper;

import com.arava.persistence.entity.AbstractEntity;
import com.arava.persistence.entity.Language;
import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.repository.LanguageRepository;
import com.arava.server.context.ContextResolver;
import com.arava.server.exception.ApiClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:17
 * All rights reserved.
 */


@Component
public class LocalizedResourceBiMapper<T extends AbstractEntity> implements Mapper<List<LocalizedResource<T>>, String>,
        ReverseMapper<List<LocalizedResource<T>>, Map<String, String>> {

  @Autowired
  private LanguageRepository languageRepository;

  @Autowired
  private ContextResolver contextResolver;

  @Override
  public String deepMap(List<LocalizedResource<T>> object) {
    Language contextLanguage = contextResolver.getLanguage();
    return object.stream()
            .filter(localizedResource -> localizedResource
                    .getLanguage()
                    .getId()
                    .equals(contextLanguage.getId())
            )
            .map(LocalizedResource::getResource)
            .findFirst()
            .orElse(null);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<LocalizedResource<T>> reverseMap(Map<String, String> object) {
    return (List<LocalizedResource<T>>) object.entrySet().stream()
            .map(entry -> LocalizedResource.builder()
                    .language(languageRepository
                            .findById(entry.getKey())
                            .orElseThrow(ApiClientException.RESOURCE_NOT_FOUND::getThrowable)
                    )
                    .resource(entry.getValue())
                    .build()
            )
            .collect(Collectors.toList());
  }
}
