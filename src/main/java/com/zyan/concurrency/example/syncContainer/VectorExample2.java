package com.zyan.concurrency.example.syncContainer;

import com.zyan.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-22 下午10:29
 */
@NotThreadSafe
public class VectorExample2 {

    public static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i < 10; i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                @Override
                public void run(){
                    for (int i = 0; i < vector.size(); i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run(){
                    for (int i = 0; i < vector.size(); i++){
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
