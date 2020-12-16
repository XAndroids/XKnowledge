package com.java.xknowledge.se.collection.set.treeset;

import java.util.Date;
import java.util.TreeSet;

/**
 * TreeSet实践2：TreeSet添加的对象必须是同一个对应，CompareTo比较的时候会进行强制类型转换同一个对象，如果不是同
 * 一个类型，回抛出类型转换异常。
 * 运行：
 * Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.util.Date
 * 	at java.util.Date.compareTo(Date.java:131)
 * 	at java.util.TreeMap.put(TreeMap.java:568)
 * 	at java.util.TreeSet.add(TreeSet.java:255)
 * 	at com.java.xknowledge.se.collection.set.treeset.TreeSetError2Test.main(TreeSetError2Test.java:11)
 *
 * Process finished with exit code 1
 * 参考：
 * 《疯狂Java讲义》
 */
class TreeSetError2Test {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        //向TreeSet集合中添加两个对象
        ts.add(new String("Struts权威指南"));
        ts.add(new Date());   //①
    }
}
