package com.java.xknowledge.se.collection.map.hashmap;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * HashMap实践：key一个为null，value多个为null
 *{null=null, a=null}//HashMap key值允许一个null
 * Exception in thread "main" java.lang.NullPointerException//Hashtable不允许null
 * 	at java.util.Hashtable.put(Hashtable.java:459)
 * 	at com.java.xknowledge.se.collection.map.hashmap.HashMapNullTest.main(HashMapNullTest.java:30)
 *
 * Process finished with exit code 1
 * 参考：
 * 《疯狂的Java讲义》
 */
class HashMapNullTest {
    public static void main(String[] args) {
        HashMap hm = new HashMap();
        //试图将两个key为null的key-value对放入HashMap中
        hm.put(null, null);
        hm.put(null, null);    //①

        //将一个value为null的key-value对放入HashMap中
        hm.put("a", null);    //②

        //输出Map对象
        System.out.println(hm);

        //Hashtable不允许key-null
        Hashtable hashtable = new Hashtable();
        hashtable.put(null, null);
    }
}
