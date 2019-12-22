package com.zyan.concurrency.example.commonUnsafe;

import com.zyan.concurrency.annoations.NotRecommend;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-21 下午10:11
 */
@Slf4j
@NotRecommend
public class DateFormatExample1 {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");


    public static int clientTotal = 5000;

    public static int threadTotal = 200;


    private static void update(){
        try {
            simpleDateFormat.parse("20191221");
        } catch (ParseException e) {
            log.error("parse exception,{}",e);
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
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception:", e);

                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}
