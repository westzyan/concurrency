package com.zyan.concurrency.example.syncContainer;

import com.zyan.concurrency.annoations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-22 下午10:29
 */
public class VectorExample3 {


    // Exception in thread "main" java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){
        for (Integer i : v1) {
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //Exception in thread "main" java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1){
        Iterator<Integer> integerIterator = v1.iterator();
        while (integerIterator.hasNext()){
            Integer i = integerIterator.next();
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //success
    private static void test3(Vector<Integer> v1){
        for (int i = 0; i < v1.size(); i++){
            if (v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }


}
