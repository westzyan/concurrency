package com.zyan.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zyan
 * @version 1.0
 * @date 20-1-1 上午10:42
 */
@Slf4j
public class FutureTaskExample {
    static class MyCallable implements Callable<String>{
        @Override
        public String call()throws Exception{
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }



    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("doing something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result:{}",result);
    }
}



