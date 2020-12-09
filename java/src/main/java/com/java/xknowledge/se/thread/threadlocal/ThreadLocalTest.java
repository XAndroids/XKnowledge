package com.java.xknowledge.se.thread.threadlocal;

/**
 * TheadLocal使用
 * 创建只能被同一个线程读写的变量，如果一段代码含有一个ThreadLocal变量的引用，即使两个线程同时执行这段代码，也无法
 * 访问对方的ThreadLocal变量
 * 运行：
 * 线程1设置threadLocal:线程1
 * 线程0设置threadLocal:线程0
 * 线程2设置threadLocal:线程2
 * 线程2设置threadLocal2:2
 * 线程0设置threadLocal2:0
 * 线程1设置threadLocal2:1
 * 线程2获取threadLocal:线程2
 * 线程0获取threadLocal:线程0
 * 线程1获取threadLocal:线程1
 * 线程0获取threadLocal2:0
 * 线程2获取threadLocal2:2
 * 线程1获取threadLocal2:1
 */
class ThreadLocalTest {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

    /**
     * 测试线程，将ThreadLocal变量的值改变，并写回，看看线程之间的值是否会影响
     */
    static class MyRunnable implements Runnable {
        int id;

        public MyRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "设置threadLocal:" + name);
            System.out.println(name + "设置threadLocal2:" + id);
            threadLocal.set(name);//多个不同的线程可以同时访问一个threadLocald对象，threadLocal保存每个线程
            threadLocal2.set(id);//独立的数据，其它线程执行这段代码无法访问其它线程的threadLocal变量
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "获取threadLocal:" + threadLocal.get());//但是每个线程通过thread
            System.out.println(name + "获取threadLocal2:" + threadLocal2.get());//Local只能获取自己保存的值
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new MyRunnable(i), "线程" + i).start();
        }
    }
}
