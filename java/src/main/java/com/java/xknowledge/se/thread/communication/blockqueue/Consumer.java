package com.java.xknowledge.se.thread.communication.blockqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者线程，从BlockingQueue中获取String，如果队列为空则阻塞当前线程，等待生产者生产String在唤醒执行
 */
class Consumer extends Thread {
    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + "消费者准备消费集合元素！");
            try {
                Thread.sleep(200);
                blockingQueue.take();//如果队列为空，则阻塞消费者线程，等待生产者生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费完成:" + blockingQueue);
        }
    }
}
