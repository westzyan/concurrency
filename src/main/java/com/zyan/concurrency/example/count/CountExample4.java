package com.zyan.concurrency.example.count;

import com.zyan.concurrency.annoations.NotThreadSafe;
import com.zyan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-17 下午10:14
 */

@Slf4j
@NotThreadSafe
public class CountExample4 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static volatile int  count = 0;

    private static synchronized void add(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception:", e);

                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }
}
