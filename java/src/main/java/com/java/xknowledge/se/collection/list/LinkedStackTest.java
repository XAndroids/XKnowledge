package com.java.xknowledge.se.collection.list;

import java.util.LinkedList;
import java.util.Stack;

/**
 * LinkedList和Stack执行栈操作性能测试
 * 测试结果：
 * count = 100000  //百万级别LinkedList耗时
 * stack time = 85
 * linkedList time = 181
 * <p>
 * count = 10000   //十万级别Stack耗时
 * stack time = 28
 * linkedList time = 10
 */
class LinkedStackTest {
    public static void main(String[] args) {
        int count = 100000;
        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            stack.push(i);
        }
        for (int i = 0; i < count; i++) {
            stack.pop();
        }
        System.out.println("stack time = " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            linkedList.push(i);
        }
        for (int i = 0; i < count; i++) {
            linkedList.pop();
        }
        System.out.println("linkedList time = " + (System.currentTimeMillis() - start2));
    }
}
