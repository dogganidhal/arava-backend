package com.arava.admin.rest.mapper;

import com.arava.admin.rest.dto.LanguageDto;
import com.arava.persistence.entity.Language;
import com.arava.server.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:46
 * All rights reserved.
 */

@Component
public class LanguageMapper implements Mapper<Language, LanguageDto> {

  @Override
  public LanguageDto deepMap(Language object) {
    return LanguageDto.builder()
            .code(object.getId())
            .name(object.getName())
            .build();
  }

}
