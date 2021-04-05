package com.java.xknowledge.leetcode.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
class QueueToStackTest {
    class MyStack {
        Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            int size = queue.size();//入栈前判断当前队列是否有元素
            queue.offer(x);
            for (int i = 0; i <= size - 1; i++) {//如果有，则将原来元素出队再入队列已达到调整出栈顺序
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }


        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
