package com.java.xknowledge.se.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * ListIterator实践：相对于Iterator增加向前迭代和添加元素功能，原来只有向后和删除
 * 运行：
 * 疯狂Java讲义
 * 轻量级Java EE企业应用实战//向前迭代，并且添加分隔符
 * =======下面开始反向迭代=======//开始向后迭代，输出元素和添加的分隔符
 * -------分隔符-------
 * 轻量级Java EE企业应用实战
 * -------分隔符-------
 * 疯狂Java讲义
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class ListIteratorTest {
    public static void main(String[] args) {
        String[] books = {"疯狂Java讲义", "轻量级Java EE企业应用实战"};
        List bookList = new ArrayList();
        for (int i = 0; i < books.length; i++) {
            bookList.add(books[i]);
        }

        ListIterator lit = bookList.listIterator();
        while (lit.hasNext()) {
            System.out.println(lit.next());
            lit.add("-------分隔符-------");
        }

        System.out.println("=======下面开始反向迭代=======");
        while (lit.hasPrevious()) {
            System.out.println(lit.previous());
        }
    }
}
