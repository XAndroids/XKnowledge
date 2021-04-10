package com.java.xknowledge.leetcode.queue;

import java.util.Stack;

/**
 * 232-用栈实现队列
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
class StackToQueueTest {
    static class MyQueue {
        private Stack<Integer> inStack;//入堆栈
        private Stack<Integer> outStack;//出队栈

        public MyQueue() {
            inStack = new Stack();
            outStack = new Stack();
        }

        //入队进入到入队栈
        public void push(int x) {
            inStack.push(x);
        }

        //出队从出队栈出栈
        public int pop() {
            inToOutStack();
            return outStack.size() > 0 ? outStack.pop() : -1;
        }

        //如果出队栈为空，则将入队栈出栈到出队栈
        private void inToOutStack() {
            if (outStack.size() == 0) {
                int inStackSize = inStack.size();
                for (int i = 0; i < inStackSize; i++) {
                    outStack.push(inStack.pop());
                }
            }
        }

        public int peek() {
            inToOutStack();
            return outStack.size() > 0 ? outStack.peek() : -1;
        }

        //两个栈都为空，则为空
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        @Override
        public String toString() {
            return "MyQueue{" +
                    "inStack=" + inStack +
                    ", outStack=" + outStack +
                    '}';
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.pop();
        myQueue.push(5);
        myQueue.pop();
        myQueue.pop();
        myQueue.pop();
        myQueue.pop();
    }
}
