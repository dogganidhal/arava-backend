package com.arava.rest.mapper;

import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:17
 * All rights reserved.
 */


@Component
public class LocalizedResourceBiMapper
        implements Mapper<Map<String, String>, List<LocalizedResource>>,
        ReverseMapper<Map<String, String>, List<LocalizedResource>> {

  @Autowired
  private LanguageRepository languageRepository;

  @Override
  public List<LocalizedResource> map(Map<String, String> object) {
    return object.entrySet().stream()
            .map(entry -> LocalizedResource.builder()
                    .resource(entry.getValue())
                    .language(languageRepository.getOne(entry.getKey()))
                    .build()
            )
            .collect(Collectors.toList());
  }

  @Override
  public Map<String, String> reverseMap(List<LocalizedResource> object) {
    Map<String, String> map = new HashMap<>();
    object.forEach(localizedResource -> map.put(
            localizedResource.getLanguage().getCode(),
            localizedResource.getResource()
    ));
    return map;
  }
}
