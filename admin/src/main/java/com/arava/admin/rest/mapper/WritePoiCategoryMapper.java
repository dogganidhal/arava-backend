package com.arava.admin.rest.mapper;

import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.PoiCategory;
import com.arava.persistence.repository.PoiTypeRepository;
import com.arava.server.dto.request.MediaWriteRequest;
import com.arava.server.dto.request.PoiCategoryWriteRequest;
import com.arava.server.exception.ApiClientException;
import com.arava.server.mapper.Mapper;
import com.arava.server.mapper.ReverseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 08:30
 * All rights reserved.
 */

@Component
public class WritePoiCategoryMapper implements Mapper<PoiCategoryWriteRequest, PoiCategory> {

  @Autowired
  private PoiTypeRepository poiTypeRepository;

  @Autowired
  private Mapper<MediaWriteRequest, Media> mediaMapper;

  @Autowired
  private ReverseMapper<List<LocalizedResource>, Map<String, String>> localizedResourceReverseMapper;

  @Override
  public PoiCategory deepMap(PoiCategoryWriteRequest object) {
    return PoiCategory.builder()
            .id(object.getId())
            .type(object.getTypeId() != null ?
                    poiTypeRepository
                            .findById(object.getTypeId())
                            .orElseThrow(ApiClientException.NOT_FOUND::getThrowable):
                    null
            )
            .icon(mediaMapper.deepMap(object.getIcon()))
            .name(localizedResourceReverseMapper.reverseMap(object.getName()))
            .build();
  }

}
