package com.arava.rest.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Nidhal Dogga
 * Date : 19/01/2020 23:03
 * All rights reserved.
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiThrowable extends Exception {

  private HttpStatus status;
  private String message;
  private LocalDateTime timestamp;

}
