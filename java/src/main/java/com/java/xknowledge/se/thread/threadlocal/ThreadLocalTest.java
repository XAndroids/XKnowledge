package com.java.xknowledge.se.thread.threadlocal;

/**
 * TheadLocal使用
 * 创建只能被同一个线程读写的变量，如果一段代码含有一个ThreadLocal变量的引用，即使两个线程同时执行这段代码，也无法
 * 访问对方的ThreadLocal变量
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
            System.out.println(name + "设置threadLocal:" + name);//但是每个线程通过threadLocal只能获取自己保存的值
            System.out.println(name + "设置threadLocal2:" + id);
            threadLocal.set(name);//多个不同的线程可以同时访问一个threadLocald对象
            threadLocal2.set(id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "获取threadLocal:" + threadLocal.get());//但是每个线程通过threadLocal只能获取自己保存的值
            System.out.println(name + "获取threadLocal2:" + threadLocal2.get());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new MyRunnable(i), "线程" + i).start();
        }
    }
}
