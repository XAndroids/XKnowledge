package com.java.xknowledge.se.thread.threadpool.executors;

class MyRunnable implements Runnable {
    private int i;

    public MyRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("currentTime = " + System.currentTimeMillis() + "，第 " + this.i +
                " Runnable，使用线程" + Thread.currentThread().getName() + "正在执行");
    }
}
