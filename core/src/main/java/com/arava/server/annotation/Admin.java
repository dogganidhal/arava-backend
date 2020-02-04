package com.arava.server.annotation;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.arava.server.configuration.OpenApiConfiguration.securitySchemeName;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 23:32
 * All rights reserved.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = securitySchemeName)
public @interface Admin {
}
