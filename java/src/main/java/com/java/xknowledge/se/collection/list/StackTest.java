package com.java.xknowledge.se.collection.list;

import java.util.Stack;

/**
 * Vector子类Stack实践：栈数据结构，提供出入栈peek和pop等操作
 * 运行：
 * [疯狂Java讲义, 轻量级Java EE企业应用实战, 疯狂Android讲义]
 * 疯狂Android讲义//出栈不移除元素
 * [疯狂Java讲义, 轻量级Java EE企业应用实战, 疯狂Android讲义]
 * 疯狂Android讲义//出栈移除元素
 * [疯狂Java讲义, 轻量级Java EE企业应用实战]
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
class StackTest {
    public static void main(String[] args) {
        Stack v = new Stack();
        //依次将三个元素push入"栈"
        v.push("疯狂Java讲义");
        v.push("轻量级Java EE企业应用实战");
        v.push("疯狂Android讲义");
        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        System.out.println(v.peek());
        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //pop出第一个元素，输出：疯狂Android讲义
        System.out.println(v.pop());
        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        System.out.println(v);
    }
}
