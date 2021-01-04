package com.java.xknowledge.arithmetic.queue.array;

/**
 * 队列数组实现测试
 */
class QueueTest {
    public static void main(String[] args) {
        Queue queue = new Queue(10);
        queue.inQueue(1);
        queue.inQueue(2);
        queue.inQueue(3);
        System.out.println("outQueue = " + queue.outQueue());

        queue.inQueue(10);
        System.out.println("outQueue = " + queue.outQueue());
    }
}
