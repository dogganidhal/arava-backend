package com.arava.rest.mapper;

import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiTheme;
import com.arava.rest.dto.PoiDto;
import com.arava.server.dto.MediaDto;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:11
 * All rights reserved.
 */


@Component
public class PoiThemeMapper implements Mapper<PoiTheme, PoiDto.PoiTheme> {

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Autowired
  private Mapper<List<LocalizedResource>, String> localizedResourceMapper;

  @Override
  public PoiDto.PoiTheme deepMap(PoiTheme object) {
    return PoiDto.PoiTheme.builder()
            .id(object.getId())
            .name(localizedResourceMapper.deepMap(object.getName()))
            .icon(mediaMapper.deepMap(object.getIcon()))
            .parent(object.getParent() != null ?
                    deepMap(object.getParent()) :
                    null
            )
            .subThemes(object.getSubThemes().stream()
                    .map(this::deepMap)
                    .collect(Collectors.toList())
            )
            .build();
  }

}
