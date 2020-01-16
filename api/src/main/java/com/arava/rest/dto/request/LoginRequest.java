package com.arava.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
  @NotBlank
  private String email;

  @NotBlank
  private String password;

}
