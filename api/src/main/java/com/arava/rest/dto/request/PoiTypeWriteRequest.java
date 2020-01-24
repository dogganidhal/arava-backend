package com.arava.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 22:53
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiTypeWriteRequest {

  private String id;

  @NotNull
  private Map<String, String> name;

  @NotNull
  private MediaWriteRequest icon;

  @Builder.Default
  private List<PoiCategoryWriteRequest> categories = new ArrayList<>();

}
