package com.arava.admin.rest.mapper;

import com.arava.admin.rest.dto.LanguageDto;
import com.arava.admin.rest.dto.LocalizedResourceDto;
import com.arava.persistence.entity.Language;
import com.arava.persistence.entity.LocalizedResource;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:48
 * All rights reserved.
 */

@Component
public class LocalizedResourceMapper implements Mapper<LocalizedResource<?>, LocalizedResourceDto> {

  @Autowired
  private Mapper<Language, LanguageDto> languageMapper;

  @Override
  public LocalizedResourceDto deepMap(LocalizedResource<?> object) {
    return LocalizedResourceDto.builder()
            .resource(object.getResource())
            .language(languageMapper.deepMap(object.getLanguage()))
            .build();
  }

}
