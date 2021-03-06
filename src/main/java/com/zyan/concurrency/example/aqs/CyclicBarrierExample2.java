package com.zyan.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-24 下午9:09
 */
@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    log.error("e", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException | TimeoutException e){
            log.warn("BarrierException", e);
        }
        log.info("{} continue",threadNum);
    }
}
