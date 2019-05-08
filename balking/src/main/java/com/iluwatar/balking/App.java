/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * In Balking Design Pattern if an object’s method is invoked when it is in an inappropriate state,
 * then the method will return without doing anything. Objects that use this pattern are generally only in a
 * state that is prone to balking temporarily but for an unknown amount of time
 *
 * Balking Design Pattern 如果对象在调用方法时状态不可调用, 方法会返回不做任何事情.
 * 对象使用这个方法只是暂时的做调用, 但是并不清楚调用的时间
 *
 * In this example implementation WashingMachine is an object that has two states
 * in which it can be: ENABLED and WASHING. If the machine is ENABLED
 * the state is changed into WASHING that any other thread can't invoke this action on this and then do the job.
 * On the other hand if it have been already washing and any other thread execute wash()
 * it can't do that once again and returns doing nothing.
 *
 * 在示例中, WashingMachine 这个对象有两个状态 [ ENABLED WASHING ]
 * 如果 machine 在 ENABLED 状态, 则将状态变为 WASHING 状态, 并且其他线程不能调用这个方法
 *
 */

public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  /**
   * @param args the command line arguments - not used
   */
  public static void main(String... args) {
    final WashingMachine washingMachine = new WashingMachine();
    // 创建 3 个固定 Executors 线程池
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    // 循环执行
    for (int i = 0; i < 3; i++) {
      executorService.execute(washingMachine::wash);
    }
    executorService.shutdown();
    try {
      executorService.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException ie) {
      LOGGER.error("ERROR: Waiting on executor service shutdown!");
    }
  }

}
