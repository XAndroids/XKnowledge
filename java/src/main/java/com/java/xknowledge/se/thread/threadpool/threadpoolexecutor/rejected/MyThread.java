package com.java.xknowledge.se.thread.threadpool.threadpoolexecutor.rejected;


class MyThread implements Runnable {
    String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程:" + Thread.currentThread().getName() + " 执行:" + name + "run");
    }
}
