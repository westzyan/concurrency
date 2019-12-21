package com.zyan.concurrency.example.singleton;


import com.zyan.concurrency.annoations.NotRecommend;
import com.zyan.concurrency.annoations.NotThreadSafe;
import com.zyan.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时候创建
 */
@ThreadSafe
@NotRecommend
//加了synchronized之后，每次只能一个线程来使用，会造成性能问题
public class SingletonExample3 {


    //私有构造函数
    private SingletonExample3(){

    }

    //单例对象
    private static SingletonExample3 instance = null;


    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
