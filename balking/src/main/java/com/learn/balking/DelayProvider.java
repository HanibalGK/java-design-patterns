package com.learn.balking;

import java.util.concurrent.TimeUnit;

/**
 * This is a DelayProvider Interface
 * Example:
 *      A : DelayProvider, Runnable
 */
public interface DelayProvider {
    void executeAfterDelay(long timeInterval, TimeUnit timeUnit, Runnable task);
}
