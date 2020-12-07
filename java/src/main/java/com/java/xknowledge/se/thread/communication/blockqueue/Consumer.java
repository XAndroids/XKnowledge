package com.java.xknowledge.se.thread.communication.blockqueue;

import java.util.concurrent.BlockingQueue;

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
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费完成:" + blockingQueue);
        }
    }
}
