package com.zyan.concurrency.example.syncContainer;

import com.zyan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-21 下午10:50
 */
@Slf4j
@ThreadSafe
public class CollectionsExample3 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());


    private static void update(int i){
        map.put(i, i);
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception:", e);

                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",map.size());
    }
}
