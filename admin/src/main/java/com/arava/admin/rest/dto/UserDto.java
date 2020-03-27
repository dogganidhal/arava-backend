package com.arava.admin.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nidhal Dogga
 * Date : 26/03/2020 18:31
 * All rights reserved.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private String id;
  private String firstName;
  private String lastName;
  private String email;

}
