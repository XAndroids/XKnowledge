package com.java.xknowledge.leetcode.stack.minstack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈
 * 链接：https://leetcode-cn.com/problems/min-stack/
 */
class MinStack {
    Deque<Integer> stack;//栈
    Deque<Integer> minStack;//最小值栈，保存每次入栈时的当前最小值

    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);//最小栈默认插入Integer.MAX_VALUE
    }

    /**
     * 将元素value入栈
     */
    public void push(int val) {
        stack.push(val);
        //只有Integer.MAX_VALUE，才能保证第一个值无论什么值都可以入最小栈
        minStack.push(Math.min(val, minStack.peek()));
    }

    /**
     * 删除栈顶元素
     */
    public void pop() {
        stack.pop();//栈中的值和最小栈的值同步
        minStack.pop();
    }

    /**
     * 获取栈顶元素
     */
    public int top() {
        return stack.peek();
    }


    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.top();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.top();
    }
}
