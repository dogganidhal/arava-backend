package com.arava.rest.util;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Nidhal Dogga
 * Date : 15/01/2020 23:32
 * All rights reserved.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('admin')")
public @interface Admin {
}
