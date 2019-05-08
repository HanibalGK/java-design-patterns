package com.learn.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WashingMachine {

    private final static Logger LOGGER = LoggerFactory.getLogger(WashingMachine.class);

    private DelayProvider delayProvider;
    private WashingMachineState washingMachineState;

    /**
     * WashingMachine
     *     初始化方法,创建线程并对其进行执行
     */
    public WashingMachine() {
        this((
                (timeInterval, timeUnit, task) -> {
            try{
                Thread.sleep(timeUnit.toMillis(timeInterval));
            } catch (Exception e) {
                e.printStackTrace();
            }
            task.run();
        }));
    }

    /**
     * 初始化
     * @param delayProvider
     */
    public WashingMachine(DelayProvider delayProvider) {
        this.delayProvider = delayProvider;
        this.washingMachineState = WashingMachineState.ENABLED;
    }

    /**
     * 获取 ${@link WashingMachine} 的状态
     * @return 运行状态
     */
    public WashingMachineState getWashingMachineState() {
        return this.washingMachineState;
    }

    /**
     * 执行
     */
    public  void wash() {
        synchronized(this) {
            LOGGER.info("{} Actual machine state {}", Thread.currentThread().getName(), getWashingMachineState());
            if(washingMachineState == WashingMachineState.WASHING) {
                LOGGER.error("ERROR: Cannot wash if the machine been already washing!");
                return;
            }
            this.washingMachineState = WashingMachineState.WASHING;
        }
        LOGGER.info("{}: doing washing ", Thread.currentThread().getName());

        this.delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS, this::endWashing);
    }

    public void endWashing() {
        washingMachineState = WashingMachineState.ENABLED;
        LOGGER.info("{} : washing compeleted ", Thread.currentThread().getName());
    }


}
