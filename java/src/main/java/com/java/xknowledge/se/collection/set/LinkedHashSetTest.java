package com.java.xknowledge.se.collection.set;

import java.util.LinkedHashSet;

/**
 * LinkedHashSet实践：
 * 使用链表保存了元素插入的顺序
 * 运行：
 * [疯狂Java讲义, 轻量级Java EE企业应用实战]//元素的插入顺序保存顺序一致
 * [轻量级Java EE企业应用实战, 疯狂Java讲义]//删除后在添加，就在末尾了！！
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
class LinkedHashSetTest {
    public static void main(String[] args) {
        LinkedHashSet books = new LinkedHashSet();
        books.add("疯狂Java讲义");
        books.add("轻量级Java EE企业应用实战");
        System.out.println(books);
        //删除 疯狂Java讲义
        books.remove("疯狂Java讲义");
        //重新添加 疯狂Java讲义
        books.add("疯狂Java讲义");
        System.out.println(books);
    }
}
