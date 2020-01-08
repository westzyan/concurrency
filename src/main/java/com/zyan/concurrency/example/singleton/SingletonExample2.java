package com.zyan.concurrency.example.singleton;


import com.zyan.concurrency.annoations.NotThreadSafe;
import com.zyan.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在第类装载时候创建
 */
@ThreadSafe
public class SingletonExample2 {

    //1。私有构造函数在实现时候没有过多的处理
    //2。这个类一定会使用，避免资源的浪费

    //私有构造函数
    private SingletonExample2(){

    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();


    //静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
