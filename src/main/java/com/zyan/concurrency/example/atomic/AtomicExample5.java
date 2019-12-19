package com.zyan.concurrency.example.atomic;

import com.zyan.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-17 下午10:14
 */

/**
 * 原子性的修改
 */

@Slf4j
@ThreadSafe
public class AtomicExample5 {


    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    /**
     * 必须为volatile,并且不能是static
     */
    @Getter
    private volatile int count = 100;


    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120)){
            log.info("update success 1 :{}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)){
            log.info("update success 2 :{}", example5.getCount());
        }else {
            log.info("update failed:{}", example5.getCount());
        }
    }
}
