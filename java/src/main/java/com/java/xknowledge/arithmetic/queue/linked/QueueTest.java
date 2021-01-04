package com.java.xknowledge.arithmetic.queue.linked;

/**
 * 队列链表实现实践
 */
class QueueTest {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.inQueue(1);
        queue.inQueue(2);
        queue.inQueue(3);
        System.out.println("outQueue = " + queue.outQueue());

        queue.inQueue(10);
        System.out.println("outQueue = " + queue.outQueue());
    }
}
