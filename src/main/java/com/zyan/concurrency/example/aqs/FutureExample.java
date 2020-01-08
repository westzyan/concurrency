package com.zyan.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zyan
 * @version 1.0
 * @date 20-1-1 上午10:42
 */
@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String>{
        @Override
        public String call()throws Exception{
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new FutureTaskExample.MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}",result);
    }
}
