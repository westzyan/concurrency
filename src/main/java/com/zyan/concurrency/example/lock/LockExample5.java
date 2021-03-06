package com.zyan.concurrency.example.lock;

import com.zyan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-17 下午10:14
 */

@Slf4j
@ThreadSafe
public class LockExample5 {

    /**
     * 少量竞争者的时候 synchronized 可以， jvm 自动解锁
     * 竞争者增长可以预估， ReentrantLock 可以，  必须自己解锁
     */

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    private final static StampedLock lock = new StampedLock();

    private static  void add(){
        long stamp = lock.writeLock();
        try{
            count++;
        }finally {
            lock.unlock(stamp);
        }
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
