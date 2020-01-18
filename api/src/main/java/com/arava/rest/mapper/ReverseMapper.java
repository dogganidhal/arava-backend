package com.arava.rest.mapper;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 10:51
 * All rights reserved.
 */

public interface ReverseMapper<T, R> {
  T reverseMap(R object);
}
