package com.zyan.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-17 下午10:14
 */

@Slf4j
public class LockExample3 {


    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final  Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try {
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value){
        writeLock.lock();
        try {
            return map.put(key, value);
        }finally {
            readLock.unlock();
        }
    }

    class Data{

    }
    // 读取很多，写很少，导致写饥饿

    public static void main(String[] args) throws InterruptedException {

    }
}
