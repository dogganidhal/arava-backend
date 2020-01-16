package com.arava.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 23:33
 * All rights reserved.
 */


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {

  private String accessToken;

  private String tokenType;

}
