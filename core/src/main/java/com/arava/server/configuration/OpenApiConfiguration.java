package com.arava.server.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
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

  @Value("${arava.server.url:http://localhost:${server.port:8080}}")
  private String serverUrl;

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
            .addServersItem(new Server()
                    .url(serverUrl)
            )
            .components(new Components()
                    .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .bearerFormat("JWT")
                            .scheme("bearer")
                    )
            );
  }

}
