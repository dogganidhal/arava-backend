package com.arava.rest.mapper;

import com.arava.persistence.entity.PoiLocalizedDescription;
import com.arava.rest.dto.PoiDto;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 15:19
 * All rights reserved.
 */


@Component
public class LocalizedDescriptionBiMapper implements Mapper<PoiLocalizedDescription, PoiDto.LocalizedDescription>,
  ReverseMapper<PoiLocalizedDescription, PoiDto.LocalizedDescription> {

  @Override
  public PoiDto.LocalizedDescription map(PoiLocalizedDescription object) {
    return PoiDto.LocalizedDescription.builder()
            .title(object.getTitle())
            .description(object.getDescription())
            .language(object.getLanguage().getName())
            .languageCode(object.getLanguageCode())
            .build();
  }

  @Override
  public PoiLocalizedDescription reverseMap(PoiDto.LocalizedDescription object) {
    return null;
  }

}
