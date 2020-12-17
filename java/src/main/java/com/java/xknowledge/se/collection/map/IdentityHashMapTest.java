package com.java.xknowledge.se.collection.map;

import java.util.IdentityHashMap;

/**
 * IdentityHashMap实践：和HashMap类似，只是key相等为==，同一个对象引用才认为相等
 * 运行：
 * {java=98, 语文=78, 语文=89}//new String("语文")是两个不同对象，两个不同的Key
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
class IdentityHashMapTest {
    public static void main(String[] args) {
        IdentityHashMap ihm = new IdentityHashMap();
        //下面两行代码将会向IdentityHashMap对象中添加两个key-value对
        ihm.put(new String("语文"), 89);
        ihm.put(new String("语文"), 78);

        //下面两行代码只会向IdentityHashMap对象中添加一个key-value对
        ihm.put("java", 93);
        ihm.put("java", 98);
        System.out.println(ihm);
    }
}
