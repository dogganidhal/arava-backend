package com.arava.server.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 21:40
 * All rights reserved.
 */


@Configuration
public class OpenApiConfiguration {

  public static final String securitySchemeName = "JWT Token";

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
            .components(new Components()
                    .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .bearerFormat("JWT")
                            .scheme("bearer")
                    )
            );
  }

}
