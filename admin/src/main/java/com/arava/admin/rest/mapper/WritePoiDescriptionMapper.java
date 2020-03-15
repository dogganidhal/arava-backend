package com.arava.admin.rest.mapper;

import com.arava.persistence.entity.PoiDetails;
import com.arava.server.dto.request.PoiWriteRequest;
import com.arava.server.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:31
 * All rights reserved.
 */


@Component
public class WritePoiDescriptionMapper implements Mapper<PoiWriteRequest.Details, PoiDetails> {

  @Override
  public PoiDetails deepMap(PoiWriteRequest.Details object) {
    return PoiDetails.builder()
            .id(object.getId())
            .address(object.getAddress())
            .email(object.getEmail())
            .website(object.getWebsite())
            .facebookUrl(object.getFacebookUrl())
            .instagramAccount(object.getInstagramAccount())
            .phone(object.getPhone())
            .openingHours(object.getOpeningHours())
            .build();
  }

}
