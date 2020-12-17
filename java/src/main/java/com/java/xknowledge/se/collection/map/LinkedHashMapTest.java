package com.java.xknowledge.se.collection.map;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap实践：通过双链表结构保存了key-value插入的顺序
 * 运行：
 * 语文------>80//和插入顺序一致
 * 英文------>82
 * 数学------>76
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap scores = new LinkedHashMap();
        scores.put("语文", 80);
        scores.put("英文", 82);
        scores.put("数学", 76);
        //遍历scores里的所有的key-value对
        for (Object key : scores.keySet()) {
            System.out.println(key + "------>" + scores.get(key));
        }
    }
}
