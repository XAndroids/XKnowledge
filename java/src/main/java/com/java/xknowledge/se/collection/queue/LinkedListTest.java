package com.java.xknowledge.se.collection.queue;

import java.util.LinkedList;

/**
 * LinkedList实践：实现的Deque接口，可以实现队列；提供了入栈出栈方法，可以模拟栈；实现了List接口，可以索引访问
 * 运行：
 * 疯狂Android讲义,轻量级Java EE企业应用实战,疯狂Java讲义,疯狂Android讲义
 * 疯狂Java讲义
 * 疯狂Android讲义
 * [轻量级Java EE企业应用实战, 疯狂Java讲义]
 * 疯狂Java讲义
 * [轻量级Java EE企业应用实战]
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
class LinkedListTest {
    public static void main(String[] args) {
        LinkedList books = new LinkedList();
        //将字符串元素加入队列的尾部
        books.offer("疯狂Java讲义");
        //将一个字符串元素加入栈的顶部
        books.push("轻量级Java EE企业应用实战");
        //将字符串元素添加到队列的头部（相当于栈的顶部）
        books.offerFirst("疯狂Android讲义");
        for (int i = 0; i < books.size(); i++) {
            System.out.print(books.get(i) + ",");
        }

        //访问、并不删除栈顶的元素
        System.out.println(books.peekFirst());
        //访问、并不删除队列的最后一个元素
        System.out.println(books.peekLast());

        //将栈顶的元素弹出“栈”
        System.out.println(books.pop());
        //下面输出将看到队列中第一个元素被删除
        System.out.println(books);

        //访问、并删除队列的最后一个元素
        System.out.println(books.pollLast());
        //下面输出将看到队列中只剩下中间一个元素：
        //轻量级Java EE企业应用实战
        System.out.println(books);
    }
}
