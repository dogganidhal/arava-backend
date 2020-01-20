package com.arava.rest.mapper;

import com.arava.persistence.entity.PoiDetails;
import com.arava.rest.dto.request.CreatePoiRequest;
import org.springframework.stereotype.Component;

import java.sql.Time;

/**
 * Created by Nidhal Dogga
 * Date : 20/01/2020 22:31
 * All rights reserved.
 */


@Component
public class CreatePoiDescriptionMapper implements Mapper<CreatePoiRequest.Details, PoiDetails> {

  @Override
  public PoiDetails map(CreatePoiRequest.Details object) {
    return PoiDetails.builder()
            .address(object.getAddress())
            .email(object.getEmail())
            .website(object.getWebsite())
            .facebookUrl(object.getFacebookUrl())
            .instagramAccount(object.getInstagramUrl())
            .phone(object.getPhone())
            .openingHour(Time.valueOf(object.getOpeningHour()))
            .closingHour(Time.valueOf(object.getClosingHour()))
            .build();
  }

}
