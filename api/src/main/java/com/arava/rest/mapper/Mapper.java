package com.arava.rest.mapper;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 10:50
 * All rights reserved.
 */

public interface Mapper<T, R> {
  R map(T object);
  default R partialMap(T object) {
    return map(object);
  }
}
