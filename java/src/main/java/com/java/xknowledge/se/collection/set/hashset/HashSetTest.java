package com.java.xknowledge.se.collection.set.hashset;

import java.awt.print.Book;
import java.util.HashSet;

/**
 * HashSet实践：存储不同的元素
 * equals和hashCode判断相等
 * 不保证加入顺序
 * 线程不安全
 * 运行：equlas和hashCode是都相同，才认为是相同对象，故A和B可以重复添加，C会被认为是同一个对象
 * [com.java.xknowledge.se.collection.set.hashset.A@2503dbd3,
 * com.java.xknowledge.se.collection.set.hashset.B@1,
 * com.java.xknowledge.se.collection.set.hashset.B@1,
 * com.java.xknowledge.se.collection.set.hashset.C@2,
 * com.java.xknowledge.se.collection.set.hashset.A@4b67cf4d]/元素的顺序和插入顺序不一致
 * <p>
 * Process finished with exit code 0
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet books = new HashSet();
        //分别向books集合中添加两个A对象，两个B对象，两个C对象
        books.add(new A());
        books.add(new A());
        books.add(new B());
        books.add(new B());
        books.add(new C());
        books.add(new C());
        for (Object book : books) {
            System.out.println(book.toString());
        }
    }
}

//类A的equals方法总是返回true,但没有重写其hashCode()方法
class A {
    public boolean equals(Object obj) {
        return true;
    }
}

//类B的hashCode()方法总是返回1,但没有重写其equals()方法
class B {
    public int hashCode() {
        return 1;
    }
}

//类C的hashCode()方法总是返回2,且有重写其equals()方法
class C {
    public int hashCode() {
        return 2;
    }

    public boolean equals(Object obj) {
        return true;
    }
}
