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
  private Mapper<LocalizedResource<?>, LocalizedResourceDto> localizedResourceMapper;

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
            .marker(object.getMarker() != null ?
                    mediaMapper.deepMap(object.getMarker()) :
                    null
            )
            .sponsoredMarker(object.getSponsoredMarker() != null ?
                    mediaMapper.deepMap(object.getSponsoredMarker()) :
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
            .subThemes(object.getSubThemes().stream()
                    .map(this::partialMap)
                    .collect(Collectors.toList())
            )
            .build();
  }

  @Override
  public PoiDto.PoiTheme partialMap(PoiTheme object) {
    return PoiDto.PoiTheme.builder()
            .id(object.getId())
            .name(object.getName().stream()
                    .map(localizedResourceMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .icon(object.getIcon() != null ?
                    mediaMapper.deepMap(object.getIcon()) :
                    null
            )
            .marker(object.getMarker() != null ?
                    mediaMapper.deepMap(object.getMarker()) :
                    null
            )
            .sponsoredMarker(object.getSponsoredMarker() != null ?
                    mediaMapper.deepMap(object.getSponsoredMarker()) :
                    null
            )
            .build();
  }

}
