package com.java.xknowledge.se.collection.set.treeset;

import java.util.TreeSet;

/**
 * TreeSet：添加元素必须时间Comparable接口
 * 运行：
 * Exception in thread "main" java.lang.ClassCastException: com.java.xknowledge.se.collection.set.treeset.Err cannot be cast to java.lang.Comparable
 * 	at java.util.TreeMap.compare(TreeMap.java:1294)
 * 	at java.util.TreeMap.put(TreeMap.java:538)
 * 	at java.util.TreeSet.add(TreeSet.java:255)
 * 	at com.java.xknowledge.se.collection.set.treeset.TreeSetErrorTest.main(TreeSetErrorTest.java:9)
 * 参考：
 * 《疯狂Java讲义》
 */
public class TreeSetErrorTest {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        //向TreeSet集合中添加两个Err对象
        ts.add(new Err());
        ts.add(new Err());  //①
    }
}

class Err {
}