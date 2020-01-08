package com.zyan.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author zyan
 * @version 1.0
 * @date 19-12-19 下午5:34
 */

@Slf4j
public class SynchronizedExample1 {

    /**
     * 作用范围，修饰代码块，作用对象：调用该方法的对象
     */
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i ++){
                log.info("test1 -{}- {}",j, i);
            }
        }
    }

    /**
     * 作用范围，修饰方法，作用对象：调用该方法的对象
     */
    public synchronized void test2(int j){
        for (int i = 0; i < 10; i ++){
            log.info("test2 -{}- {}",j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test2(1);
        });

        executorService.execute(()->{
            example2.test2(2);
        });
    }

}
