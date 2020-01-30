package com.arava.rest.mapper;

import com.arava.persistence.entity.Rating;
import com.arava.rest.dto.PoiDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 11:07
 * All rights reserved.
 */


@Component
public class RatingsBiMapper implements Mapper<List<Rating>, PoiDto.Ratings>, ReverseMapper<List<Rating>, PoiDto.Ratings> {

  @Override
  public PoiDto.Ratings deepMap(List<Rating> objects) {
    return PoiDto.Ratings.builder()
            .averageScore(objects.stream()
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

  @Override
  public List<Rating> reverseMap(PoiDto.Ratings object) {
    return null;
  }

}
