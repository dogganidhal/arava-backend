package com.arava.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Nidhal Dogga
 * Date : 02/03/2020 10:34
 * All rights reserved.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
  
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  @NotNull
  private String oldPassword;

}
