package com.arava.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by Nidhal Dogga
 * Date : 21/01/2020 08:28
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoiThemeWriteRequest {

  private String id;
  private String parentId;
  @NotNull
  private Map<String, String> name;
  @NotNull
  private MediaWriteRequest icon;
  private List<PoiThemeWriteRequest> subThemes;

}
