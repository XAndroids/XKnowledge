package com.java.xknowledge.se.thread.communication.blockqueue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者线程，从strArr中生产字符串，加入到BlockingQueue，如果Blocking满了则阻塞当前生产者线程，等待消费者线程
 * 消费后，在唤醒生产者生产
 */
class Producer extends Thread {
    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{"Java", "Struts", "Spring"};
        for (int i = 0; i < 99; i++) {
            System.out.println(getName() + "生产者准备生产集合元素！");
            try {
                Thread.sleep(200);
                blockingQueue.put(strArr[i % 3]);//生产字符串，如果队列满了则阻塞线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产完成:" + blockingQueue);
        }

    }
}
