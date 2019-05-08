package com.learn.balking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    final static Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        final WashingMachine washingMachine = new WashingMachine();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i ++) {
            executorService.execute(washingMachine::wash);
        }
        executorService.shutdown();
        try{
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        }catch(Exception e) {
            LOGGER.error("ERROR: Waiting on Executor service shutdown");
        }
    }
}
