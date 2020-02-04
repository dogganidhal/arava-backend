package com.arava.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 07:07
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

  @Email
  @NotNull
  private String email;

  @NotNull
  private String password;

}
