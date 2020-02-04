package com.arava.server.mapper;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 10:50
 * All rights reserved.
 */

public interface Mapper<T, R> {
  R deepMap(T object);
  default R partialMap(T object) {
    return deepMap(object);
  }
}
