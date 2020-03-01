package com.arava.server.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 19/01/2020 22:57
 * All rights reserved.
 */

public enum ApiClientException implements ApiException {

  BAD_CREDENTIALS {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.AUTH_BAD_CREDENTIALS;
    }

    @Override
    public String getMessage() {
      return "Bad credentials";
    }
  },
  MISSING_CREDENTIALS {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.AUTH_MISSING_CREDENTIALS;
    }
  },
  UNAUTHORIZED {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getMessage() {
      return "Authorization required to access requested resource";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.AUTH_UNAUTHORIZED;
    }
  },
  USER_EXISTS {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
      return "User exists";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.AUTH_USER_EXISTS;
    }
  },
  ISLAND_NOT_FOUND {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
      return "No island with specified id was found";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.ISLAND_NOT_FOUND;
    }
  },
  ARCHIPELAGO_NOT_FOUND {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
      return "No archipelago with specified id was found";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.ARCHIPELAGO_NOT_FOUND;
    }
  },
  POI_NOT_FOUND {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
      return "No entity with specified id was found";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.POI_NOT_FOUND;
    }
  },
  THEME_NOT_FOUND {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
      return "No theme with specified id was found";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.THEME_NOT_FOUND;
    }
  },
  RESOURCE_NOT_FOUND {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
      return "No resource specified id was found";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.RESOURCE_NOT_FOUND;
    }
  },
  ISLAND_VALIDATION_FAILED {
    @Override
    public HttpStatus getStatus() {
      return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
      return "Validation failed for request, please check your input";
    }

    @Override
    public ErrorCode getErrorCode() {
      return ErrorCode.ISLAND_VALIDATION_FAILED;
    }
  };

  @Override
  public LocalDateTime getTimestamp() {
    return LocalDateTime.now();
  }

  @Override
  public ApiThrowable getThrowable() {
    return ApiThrowable.builder()
            .message(getMessage())
            .errorCode(getErrorCode())
            .status(getStatus())
            .timestamp(getTimestamp())
            .build();
  }

}
