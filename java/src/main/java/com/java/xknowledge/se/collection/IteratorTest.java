package com.java.xknowledge.se.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Iterator：
 * 遍历（即迭代）访问Collection集合中的元素，成为迭代器；
 * 隐藏了Coolection实现类的底层细节，提供遍历统一接口；
 * 仅用于遍历集合，本身并不盛装对象能力；
 * 运行：
 * 疯狂Android讲义
 * 轻量级Java EE企业应用实战
 * 疯狂Java讲义
 * [疯狂Android讲义, 轻量级Java EE企业应用实战]
 *
 * Process finished with exit code 0
 * 参考《疯狂Java讲义》
 */
class IteratorTest {
    public static void main(String[] args) {
        //创建一个集合
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");

        //获取books集合对应的迭代器
        Iterator it = books.iterator();
        while (it.hasNext()) {
            //it.next()方法返回的数据类型是Object类型，
            //需要强制类型转换
            String book = (String) it.next();
            System.out.println(book);
            if (book.equals("疯狂Java讲义")) {
                //从集合中删除上一次next方法返回的元素
                it.remove();
            }
            //注意：对book变量赋值，不会改变集合元素本身!!!!!
            book = "测试字符串";   //①
        }

        System.out.println(books);
    }
}
