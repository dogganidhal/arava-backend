package com.arava.server.mapper;

import com.arava.persistence.entity.Rating;
import com.arava.server.dto.RatingsDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:07
 * All rights reserved.
 */


@Component
public class RatingsMapper implements Mapper<List<Rating>, RatingsDto> {

  @Override
  public RatingsDto deepMap(List<Rating> objects) {
    return RatingsDto.builder()
            .averageScore(objects.stream()
                    .filter(rating -> rating.getScore() != null)
                    .reduce((lhs, rhs) -> Rating.builder()
                            .score((lhs.getScore() + rhs.getScore()) / 2)
                            .build()
                    )
                    .orElseGet(() -> Rating.builder()
                            .score(0.0)
                            .build()
                    )
                    .getScore()
            )
            .count(objects.size())
            .build();
  }

}
