package com.java.xknowledge.se.collection.map;

import java.util.WeakHashMap;

/**
 * WeakHashMap实践：key对象弱引用保存，如果没有被其它强引用对象引用，可能会被垃圾回收
 * 运行：
 * {英文=中等, java=中等, 数学=及格, 语文=良好}
 * {java=中等}//临时变量new String()key GC后都被回收了
 * 不要让key所引用的对象具有任何强引用，否则失去意义
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap whm = new WeakHashMap();
        //将WeakHashMap中添加三个key-value对，
        //三个key都是匿名字符串对象（没有其他引用）
        whm.put(new String("语文"), new String("良好"));
        whm.put(new String("数学"), new String("及格"));
        whm.put(new String("英文"), new String("中等"));

        //将WeakHashMap中添加一个key-value对，
        //该key是一个系统缓存的字符串对象。
        whm.put("java", new String("中等"));

        //输出whm对象，将看到4个key-value对。
        System.out.println(whm);

        //通知系统立即进行垃圾回收
        System.gc();
        System.runFinalization();

        //通常情况下，将只看到一个key-value对。
        System.out.println(whm);
    }
}
