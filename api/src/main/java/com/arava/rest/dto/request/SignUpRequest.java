package com.arava.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Nidhal Dogga
 * Date : 16/01/2020 07:19
 * All rights reserved.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

  @Email
  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  @Size(min = 3)
  private String firstName;

  @NotNull
  @Size(min = 2)
  private String lastName;

}
