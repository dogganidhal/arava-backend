package com.arava.rest.mapper;

import com.arava.persistence.entity.PoiLocalizedDescription;
import com.arava.persistence.repository.LanguageRepository;
import com.arava.rest.dto.request.PoiWriteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:17
 * All rights reserved.
 */


@Component
public class WriteLocalizedDescriptionMapper
        implements Mapper<PoiWriteRequest.LocalizedDescription, PoiLocalizedDescription> {

  @Autowired
  private LanguageRepository languageRepository;

  @Override
  public PoiLocalizedDescription map(PoiWriteRequest.LocalizedDescription object) {
    return PoiLocalizedDescription.builder()
            .id(object.getId())
            .title(object.getTitle())
            .description(object.getDescription())
            .language(languageRepository.getOne(object.getLanguageCode()))
            .build();
  }

}
