package com.java.xknowledge.se.collection.map.hashmap;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * HashMap线程安全问题：多线程put数据丢失
 * 运行：
 * thread = Thread[pool-1-thread-1,5,main],put= 1
 * thread = Thread[pool-1-thread-2,5,main],put= 2
 * thread = Thread[pool-1-thread-3,5,main],put= 3
 * thread = Thread[pool-1-thread-4,5,main],put= 4
 * thread = Thread[pool-1-thread-5,5,main],put= 5
 * thread = Thread[pool-1-thread-6,5,main],put= 6
 * thread = Thread[pool-1-thread-7,5,main],put= 7
 * thread = Thread[pool-1-thread-8,5,main],put= 8
 * thread = Thread[pool-1-thread-9,5,main],put= 9
 * thread = Thread[pool-1-thread-10,5,main],put= 10
 * {2=test:2, 3=test:3, 4=test:4, 5=test:5, 6=test:6, 7=test:7, 8=test:8, 9=test:9, 10=test:10}
 * 参考：https://www.jianshu.com/p/59a2d8e6f296
 */
class HashMapThreadSafeTest {
    private static HashMap<Integer, String> mHashMap = new HashMap<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                System.out.println("thread = " + Thread.currentThread() + ",put= " + finalI);
                mHashMap.put(finalI, "test:" + finalI);
            });
        }

        //增加延迟，避免遍历和put修改的异常
        //Exception in thread "main" java.util.ConcurrentModificationException
        //	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1437)
        //	at java.util.HashMap$EntryIterator.next(HashMap.java:1471)
        //	at java.util.HashMap$EntryIterator.next(HashMap.java:1469)
        //	at java.util.AbstractMap.toString(AbstractMap.java:554)
        //	at java.lang.String.valueOf(String.java:2994)
        //	at java.io.PrintStream.println(PrintStream.java:821)
        //	at com.java.xknowledge.se.collection.map.hashmap.HashMapThreadSafeTest.main(HashMapThreadSafeTest.java:22)
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mHashMap);
    }
}
