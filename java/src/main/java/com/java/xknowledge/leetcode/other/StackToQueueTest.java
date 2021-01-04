package com.java.xknowledge.leetcode.other;

import java.util.Stack;

/**
 * 232-用栈实现队列？
 * 参考：leetCode
 */
class StackToQueueTest {
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

    static class MyQueue {
        private Stack<Integer> inStack;
        private Stack<Integer> outStack;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            inStack = new Stack();
            outStack = new Stack();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            inStack.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            inToOutStack();
            return outStack.pop();
        }

        private void inToOutStack() {
            if (outStack.size() == 0) {
                int inStackSize = inStack.size();
                for (int i = 0; i < inStackSize; i++) {
                    outStack.push(inStack.pop());
                }
            }
        }

        /**
         * Get the front element.
         */
        public int peek() {
            inToOutStack();
            return outStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
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
}
