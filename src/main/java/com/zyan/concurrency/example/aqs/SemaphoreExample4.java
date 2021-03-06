package com.zyan.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-24 下午5:27
 */

@Slf4j
public class SemaphoreExample4 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){ //尝试获取一个许可
                        test(threadNum);
                        semaphore.release();//释放一个许可
                    }
                }catch (Exception e){
                    log.error("exception", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
