package com.arava.persistence.merger;

/**
 * Created by Nidhal Dogga
 * Date : 30/01/2020 10:04
 * All rights reserved.
 */

public interface EntityMerger<T> {

  T merge(T src, T dest);
  default <R> R pick(R src, R dest) {
    return dest != null ? dest : src;
  }

}
