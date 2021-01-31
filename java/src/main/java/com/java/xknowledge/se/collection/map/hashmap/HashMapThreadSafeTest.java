package com.java.xknowledge.se.collection.map.hashmap;

//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * HashMap线程安全问题：
 * 1.多线程Put操作，导致元素丢失；
 * 2.多线程Put非Null元素后，Get操作得到Null值；
 * 3.多线程Put操作后，Get操作导致死循环（resize死循环），JDK8红黑树已修复；
 * 参考：
 * https://juejin.cn/post/6844903796225605640#heading-0
 * https://www.jianshu.com/p/59a2d8e6f296
 * https://www.jianshu.com/p/e28792ee30ab
 * https://cloud.tencent.com/developer/article/1120823
 */
class HashMapThreadSafeTest {
    //    private static Map<Integer, String> mHashMap = new HashMap<>();
    //方案1：使用hashTable线程安全
    //    private static Map<Integer, String> mHashMap = new Hashtable<>();
    //方案2：使用Collections.synchronizedMap返回线程安全Map
    //    private static Map<Integer, String> mHashMap = Collections.synchronizedMap(new HashMap<>());
    //方案3：使用ConcurrentHashMap线程安全
        private static Map<Integer, String> mHashMap = new ConcurrentHashMap<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(20);

    // * 运行：
    // * thread = Thread[pool-1-thread-1,5,main],put= 1
    // * thread = Thread[pool-1-thread-2,5,main],put= 2
    // * thread = Thread[pool-1-thread-3,5,main],put= 3
    // * thread = Thread[pool-1-thread-4,5,main],put= 4
    // * thread = Thread[pool-1-thread-5,5,main],put= 5
    // * thread = Thread[pool-1-thread-6,5,main],put= 6
    // * thread = Thread[pool-1-thread-7,5,main],put= 7
    // * thread = Thread[pool-1-thread-8,5,main],put= 8
    // * thread = Thread[pool-1-thread-9,5,main],put= 9
    // * thread = Thread[pool-1-thread-10,5,main],put= 10
    // * {2=test:2, 3=test:3, 4=test:4, 5=test:5, 6=test:6, 7=test:7, 8=test:8, 9=test:9, 10=test:10}
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

    // * 运行：
    // * thread = Thread[main,5,main],get= 334278,value =test:334278
    // * thread = Thread[main,5,main],get= 334279,value =null
    // * thread = Thread[main,5,main],get= 334280,value =test:334280
    // * thread = Thread[main,5,main],get= 334281,value =test:334281
    // * thread = Thread[main,5,main],get= 334282,value =test:334282
    // * thread = Thread[main,5,main],get= 334283,value =null
    // * thread = Thread[main,5,main],get= 334284,value =test:334284
    // * thread = Thread[main,5,main],get= 334285,value =test:334285
    // * thread = Thread[main,5,main],get= 334286,value =test:334286
    // * thread = Thread[main,5,main],get= 334287,value =null
    public static void main2(String[] args) {
        for (int i = 1; i <= 1000000; i++) {
            int finalI = i;
            executorService.submit(() -> {
//                System.out.println("thread = " + Thread.currentThread() + ",put= " + finalI);
                mHashMap.put(finalI, "test:" + finalI);
            });
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 1000000; i++) {
            String value = mHashMap.get(i);
            System.out.println("thread = " + Thread.currentThread() + ",get= " + i + ",value =" + value);
        }
    }
}
