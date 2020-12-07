package com.java.xknowledge.se.thread.communication.blockqueue;

import java.util.concurrent.BlockingQueue;

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
                blockingQueue.put(strArr[i % 3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产完成:" + blockingQueue);
        }

    }
}
