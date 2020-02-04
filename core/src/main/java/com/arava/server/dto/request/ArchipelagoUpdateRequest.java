package com.arava.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Nidhal Dogga
 * Date : 28/01/2020 19:18
 * All rights reserved.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArchipelagoUpdateRequest {

  @NotNull
  private String id;
  private String name;

}
