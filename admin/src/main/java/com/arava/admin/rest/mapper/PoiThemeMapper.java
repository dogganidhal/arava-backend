package com.arava.admin.rest.mapper;

import com.arava.admin.rest.dto.LocalizedResourceDto;
import com.arava.admin.rest.dto.PoiDto;
import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiTheme;
import com.arava.server.dto.MediaDto;
import com.arava.server.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:53
 * All rights reserved.
 */

@Component
public class PoiThemeMapper implements Mapper<PoiTheme, PoiDto.PoiTheme> {

  @Autowired
  private Mapper<LocalizedResource, LocalizedResourceDto> localizedResourceMapper;

  @Autowired
  private Mapper<Media, MediaDto> mediaMapper;

  @Override
  public PoiDto.PoiTheme deepMap(PoiTheme object) {
    return PoiDto.PoiTheme.builder()
            .id(object.getId())
            .icon(object.getIcon() != null ?
                    mediaMapper.deepMap(object.getIcon()) :
                    null
            )
            .name(object.getName().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .parent(object.getParent() != null ?
                    deepMap(object.getParent()) :
                    null
            )
            .build();
  }
}
