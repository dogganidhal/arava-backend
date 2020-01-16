package com.arava.rest.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 21:40
 * All rights reserved.
 */


@Configuration
public class OpenApiConfiguration {

  private static final String securitySchemeKey = "JWT Token";
  private static final SecurityRequirement securityRequirement = new SecurityRequirement()
          .addList(securitySchemeKey);

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
            .components(new Components()
                    .addSecuritySchemes(securitySchemeKey, new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .bearerFormat("JWT")
                            .scheme("bearer")
                    )
            )
            .security(Collections.singletonList(securityRequirement));
  }

}
