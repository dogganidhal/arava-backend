package com.arava.rest.dto.response;

import com.arava.rest.dto.PoiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 23:44
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {

  private List<PoiDto> pois;
  private Integer count;

}
