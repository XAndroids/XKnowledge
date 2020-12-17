package com.java.xknowledge.se.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * List使用实践2:List中通过equals判断是否是同一个元素，如books.indexOf(new String("疯狂Ajax讲义"))等方法
 * 运行：
 * [轻量级Java EE企业应用实战, 疯狂Java讲义, 疯狂Android讲义]
 * [疯狂Java讲义, 疯狂Android讲义]//A equals true默认所有元素相同，删除第1个
 * [疯狂Android讲义]
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class List2Test {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));
        System.out.println(books);

        //删除集合中A对象，将导致第一个元素被删除
        //因为A对象equals始终返回true，故会认为任何元素相同，默认删除第一个
        books.remove(new A());     //①
        System.out.println(books);

        //删除集合中A对象，再次删除集合中第一个元素
        books.remove(new A());     //②
        System.out.println(books);
    }
}

class A {
    public boolean equals(Object obj) {
        return true;
    }
}
