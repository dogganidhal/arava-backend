package com.arava.admin.rest.mapper;

import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiTheme;
import com.arava.persistence.repository.PoiThemeRepository;
import com.arava.server.dto.request.MediaWriteRequest;
import com.arava.server.dto.request.PoiThemeWriteRequest;
import com.arava.server.exception.ApiClientException;
import com.arava.server.mapper.Mapper;
import com.arava.server.mapper.ReverseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 08:30
 * All rights reserved.
 */

@Component
public class WritePoiThemeMapper implements Mapper<PoiThemeWriteRequest, PoiTheme> {

  @Autowired
  private PoiThemeRepository poiThemeRepository;

  @Autowired
  private Mapper<MediaWriteRequest, Media> mediaMapper;

  @Autowired
  private ReverseMapper<List<LocalizedResource<PoiTheme>>, Map<String, String>> localizedResourceReverseMapper;

  @Override
  public PoiTheme deepMap(PoiThemeWriteRequest object) {
    return PoiTheme.builder()
            .id(object.getId())
            .parent(object.getParentId() != null ?
                    poiThemeRepository
                            .findById(object.getParentId())
                            .orElseThrow(ApiClientException.THEME_NOT_FOUND::getThrowable):
                    null
            )
            .subThemes(object.getSubThemes() != null ?
                    object.getSubThemes().stream()
                            .map(this::deepMap)
                            .collect(Collectors.toList()) :
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
            .icon(mediaMapper.deepMap(object.getIcon()))
            .name(localizedResourceReverseMapper.reverseMap(object.getName()))
            .build();
  }

}
