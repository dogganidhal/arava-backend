package com.arava.rest.annotation;

/**
 * Created by Nidhal Dogga
 * Date : 19/01/2020 21:03
 * All rights reserved.
 */

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

import static com.arava.rest.configuration.OpenApiConfiguration.securitySchemeName;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = securitySchemeName)
public @interface Authenticated {}