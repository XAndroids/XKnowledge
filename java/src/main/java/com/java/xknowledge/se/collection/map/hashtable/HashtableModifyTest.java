package com.java.xknowledge.se.collection.map.hashtable;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Hashtable/HashMap实践：动态修改作为key的可变对象，引入key相同场景，无法准确访问修改过的key对象
 * 运行：
 * {com.java.xknowledge.se.collection.map.hashtable.A@1560b=疯狂Java讲义,com.java.xknowledge.se.collection.map.hashtable.A@1560b=轻量级Java EE企业应用实战}
 * {com.java.xknowledge.se.collection.map.hashtable.A@1560b=疯狂Java讲义}
 * null
 * null
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class HashtableModifyTest {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        //此处的A类与前一个程序的A类是同一个类
        ht.put(new A(60000), "疯狂Java讲义");
        ht.put(new A(87563), "轻量级Java EE企业应用实战");

        //获得Hashtable的key Set集合对应的Iterator迭代器
        Iterator it = ht.keySet().iterator();
        //取出Map中第一个key
        A first = (A) it.next();
        first.count = 87563;   //①
        //输出{A@1560b=疯狂Java讲义, A@1560b=轻量级Java EE企业应用实战}
        System.out.println(ht);

        //只能删除没有被修改过的key所对应的key-value对
        ht.remove(new A(87563));
        System.out.println(ht);

        //无法获取剩下的value，下面两行代码都将输出null。
        System.out.println(ht.get(new A(87563)));  //②
        System.out.println(ht.get(new A(60000)));   //③
    }
}
