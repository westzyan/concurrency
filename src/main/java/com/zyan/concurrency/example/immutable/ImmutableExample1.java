package com.zyan.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.zyan.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author zyan
 * @version 1.0
 * @date 19-12-21 下午4:20
 */

@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        map.put(1, 3 );
        log.info("{}",map.get(1));
    }

    private void test(final int a){

    }
}
