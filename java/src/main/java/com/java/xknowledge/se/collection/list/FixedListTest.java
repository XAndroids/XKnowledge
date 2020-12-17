package com.java.xknowledge.se.collection.list;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays.asList()：固定长度List结合，它是Arrays.ArrayList内部类，不是ArrayList；
 * 运行：
 * class java.util.Arrays$ArrayList//Arrys$ArrayList内部类
 * 疯狂Java讲义
 * 轻量级Java EE企业应用实战
 * Exception in thread "main" java.lang.UnsupportedOperationException//不支持移除！！
 * at java.util.AbstractList.add(AbstractList.java:148)
 * at java.util.AbstractList.add(AbstractList.java:108)
 * at com.java.xknowledge.se.collection.list.FixedListTest.main(FixedListTest.java:25)
 *
 * Process finished with exit code 1
 * 参考：
 * 《疯狂Java讲义》
 */
class FixedListTest {
    public static void main(String[] args) {
        List fixedList = Arrays.asList("疯狂Java讲义", "轻量级Java EE企业应用实战");
        //获取fixedList的实现类，将输出Arrays$ArrayList
        System.out.println(fixedList.getClass());

        //遍历fixedList的集合元素
        for (int i = 0; i < fixedList.size(); i++) {
            System.out.println(fixedList.get(i));
        }

        //试图增加、删除元素都会引发UnsupportedOperationException异常
        fixedList.add("疯狂Android讲义");
        fixedList.remove("疯狂Java讲义");
    }
}
