package jdk.map.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Will Liu
 * @date 2021/8/12
 */
public class T01_ConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, "java" + i);
        }
    }

}
