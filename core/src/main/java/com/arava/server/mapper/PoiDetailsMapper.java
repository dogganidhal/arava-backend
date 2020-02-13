package com.arava.server.mapper;

import com.arava.persistence.entity.PoiDetails;
import com.arava.server.dto.PoiDetailsDto;
import org.springframework.stereotype.Component;

/**
 * Created by Nidhal Dogga
 * Date : 09/02/2020 16:39
 * All rights reserved.
 */

@Component
public class PoiDetailsMapper implements Mapper<PoiDetails, PoiDetailsDto> {

  @Override
  public PoiDetailsDto deepMap(PoiDetails object) {
    return PoiDetailsDto.builder()
            .address(object.getAddress())
            .phone(object.getPhone())
            .email(object.getEmail())
            .facebookUrl(object.getFacebookUrl())
            .instagramAccount(object.getInstagramAccount())
            .website(object.getWebsite())
            .closingHour(object.getClosingHour() != null ?
                    object.getClosingHour().toLocalTime() :
                    null
            )
            .openingHour(object.getOpeningHour() != null ?
                    object.getOpeningHour().toLocalTime() :
                    null
            )
            .build();
  }

}
