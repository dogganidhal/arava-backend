package com.arava.rest.mapper;

import com.arava.persistence.entity.PoiDetails;
import com.arava.rest.dto.request.PoiWriteRequest;
import org.springframework.stereotype.Component;

import java.sql.Time;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:31
 * All rights reserved.
 */


@Component
public class WritePoiDescriptionMapper implements Mapper<PoiWriteRequest.Details, PoiDetails> {

  @Override
  public PoiDetails map(PoiWriteRequest.Details object) {
    return PoiDetails.builder()
            .id(object.getId())
            .address(object.getAddress())
            .email(object.getEmail())
            .website(object.getWebsite())
            .facebookUrl(object.getFacebookUrl())
            .instagramAccount(object.getInstagramUrl())
            .phone(object.getPhone())
            .openingHour(object.getOpeningHour() != null ? Time.valueOf(object.getOpeningHour()) : null)
            .closingHour(object.getClosingHour() != null ? Time.valueOf(object.getClosingHour()) : null)
            .build();
  }

}
