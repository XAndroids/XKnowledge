package com.java.xknowledge.se.collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * new HashMap<>(customParameters)，多线程修改customParameters造成的ConcurrentModificationException测试
 * 参考：https://www.cnblogs.com/wangkundentisy/p/6659321.html
 */
class ConcurrentModificationTest {
    //方案：
    //0.加锁
    //
    //1.Hashtable会报错
    //Exception in thread "main" java.util.ConcurrentModificationException
    // * 	at java.util.Hashtable$Enumerator.next(Hashtable.java:1378)
    // * 	at java.util.HashMap.putMapEntries(HashMap.java:511)
    // * 	at java.util.HashMap.<init>(HashMap.java:489)
    // * 	at com.java.xknowledge.se.collection.map.ConcurrentModificationTest.main(ConcurrentModificationTest.java:37)
    //2.Collections.synchronizedMap(new LinkedHashMap<>())会报错
    //Exception in thread "main" java.util.ConcurrentModificationException
    //	at java.util.LinkedHashMap$LinkedHashIterator.nextNode(LinkedHashMap.java:719)
    //	at java.util.LinkedHashMap$LinkedEntryIterator.next(LinkedHashMap.java:752)
    //	at java.util.LinkedHashMap$LinkedEntryIterator.next(LinkedHashMap.java:750)
    //	at java.util.HashMap.putMapEntries(HashMap.java:511)
    //	at java.util.HashMap.<init>(HashMap.java:489)
    //	at com.java.xknowledge.se.collection.map.ConcurrentModificationTest.main(ConcurrentModificationTest.java:46)
    //3.new ConcurrentHashMap<>()没报错
    //FIXME 这三种方案，都是线程安全，为什么有些生效，有些不生效？？
    private static final Map<String, String> customParameters = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("ConcurrentHashMap");
        //异步线程修改customParameters
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("thread = " + Thread.currentThread() + "customParameters.put , i = " + i);
                customParameters.put(String.valueOf(i), String.valueOf(i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("thread = " + Thread.currentThread() + "customParameters.get , i = " + i);
                customParameters.get(String.valueOf(i));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("thread = " + Thread.currentThread() + "customParameters.remove , i = " + i);
                customParameters.remove(String.valueOf(i));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("thread = " + Thread.currentThread() + "customParameters.clear() , i = " + i);
                customParameters.clear();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        //主线程new HashMap<>(customParameters)
        for (int i = 0; i < 1000; i++) {
            System.out.println("thread = " + Thread.currentThread() + ", i = " + i);
            new HashMap<>(customParameters);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
