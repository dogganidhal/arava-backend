package com.arava.admin.rest.mapper;

import com.arava.persistence.entity.LocalizedResource;
import com.arava.persistence.entity.Media;
import com.arava.persistence.entity.Poi;
import com.arava.persistence.entity.PoiDetails;
import com.arava.persistence.repository.IslandRepository;
import com.arava.persistence.repository.PoiThemeRepository;
import com.arava.persistence.repository.UserRepository;
import com.arava.server.dto.request.MediaWriteRequest;
import com.arava.server.dto.request.PoiWriteRequest;
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
 * Date : 20/01/2020 21:50
 * All rights reserved.
 */


@Component
public class WritePoiMapper implements Mapper<PoiWriteRequest, Poi> {

  @Autowired
  private IslandRepository islandRepository;

  @Autowired
  private PoiThemeRepository poiThemeRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ReverseMapper<List<LocalizedResource<Poi>>, Map<String, String>> localizedResourceReverseMapper;

  @Autowired
  private Mapper<MediaWriteRequest, Media> mediaMapper;

  @Autowired
  private Mapper<PoiWriteRequest.Details, PoiDetails> detailsMapper;

  @Override
  public Poi deepMap(PoiWriteRequest object) {
    return Poi.builder()
            .id(object.getId())
            .latitude(object.getLatitude())
            .longitude(object.getLongitude())
            .owner(object.getOwnerId() != null ?
                    userRepository
                            .findById(object.getOwnerId())
                            .orElseThrow(ApiClientException.USER_NOT_FOUND::getThrowable) :
                    null
            )
            .island(object.getIslandId() != null ?
                    islandRepository
                            .findById(object.getIslandId())
                            .orElseThrow(ApiClientException.ISLAND_NOT_FOUND::getThrowable) :
                    null
            )
            .theme(object.getThemeId() != null ?
                    poiThemeRepository
                            .findById(object.getThemeId())
                            .orElseThrow(ApiClientException.THEME_NOT_FOUND::getThrowable):
                    null
            )
            .mainImage(object.getMainImage() != null ?
                    mediaMapper.deepMap(object.getMainImage()) :
                    null
            )
            .draft(object.getDraft())
            .featured(object.getFeatured())
            .sponsored(object.getSponsored())
            .activity(object.getActivity())
            .premium(object.getPremium())
            .details(detailsMapper.deepMap(object.getDetails()))
            .title(localizedResourceReverseMapper.reverseMap(object.getTitle()))
            .description(object.getDescription() != null ?
                    localizedResourceReverseMapper.reverseMap(object.getDescription()) :
                    null
            )
            .medias(object.getMedias().stream()
                    .map(mediaMapper::deepMap)
                    .collect(Collectors.toList())
            )
            .build();
  }

}
