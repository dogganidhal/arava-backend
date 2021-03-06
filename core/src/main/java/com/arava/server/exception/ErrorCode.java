package com.arava.server.exception;

/**
 * Created by Nidhal Dogga
 * Date : 01/03/2020 08:44
 * All rights reserved.
 */

public enum ErrorCode {
  AUTH_USER_EXISTS,
  AUTH_BAD_CREDENTIALS,
  AUTH_MISSING_CREDENTIALS,
  AUTH_UNAUTHORIZED,

  POI_NOT_FOUND,
  POI_USER_NOT_FOUND,

  ISLAND_NOT_FOUND,
  ISLAND_VALIDATION_FAILED,

  ARCHIPELAGO_NOT_FOUND,
  THEME_NOT_FOUND,
  RESOURCE_NOT_FOUND,

  COMMENT_NOT_FOUND,

  GENERAL_INTERNAL_SERVER_ERROR,
  GENERAL_VALIDATION_ERROR
}
