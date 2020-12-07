package com.java.xknowledge.se.thread.communication.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 线层通信-BlockingQueue
 * 几个生产者、多个消费者共同生产和消费阻塞队列数据
 * Thread-1生产者准备生产集合元素！
 * Thread-0生产者准备生产集合元素！
 * Thread-2生产者准备生产集合元素！
 * Thread-3消费者准备消费集合元素！
 * Thread-2生产完成:[Java]
 * Thread-2生产者准备生产集合元素！
 * Thread-3消费完成:[]
 * Thread-3消费者准备消费集合元素！
 * Thread-1生产完成:[Java]
 * Thread-1生产者准备生产集合元素！
 * Thread-3消费完成:[]
 * 参考：《疯狂的Java讲义》
 */
class BlockQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        //启动3个生产者
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();
        //启动一个消费者
        new Consumer(blockingQueue).start();
    }
}
