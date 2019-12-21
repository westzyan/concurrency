package com.zyan.concurrency.example.singleton;


import com.zyan.concurrency.annoations.Recommend;
import com.zyan.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式
 * 最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {


    //私有构造函数
    private SingletonExample7(){

    }
    //静态的工厂方法
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM 保证这个方法绝对只是调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
