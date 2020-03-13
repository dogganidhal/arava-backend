package com.arava.business.manager;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nidhal Dogga
 * Date : 12/03/2020 18:18
 * All rights reserved.
 */

public interface RetryPolicy {

  static <T> Retryer<T> defaultRetryer() {
    return RetryerBuilder.<T>newBuilder()
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();
  }

  static <T> Retryer<T> exponentialBackoff() {
    return RetryerBuilder.<T>newBuilder()
            .withWaitStrategy(WaitStrategies.exponentialWait(100, 5, TimeUnit.MINUTES))
            .withStopStrategy(StopStrategies.neverStop())
            .build();
  }

}
