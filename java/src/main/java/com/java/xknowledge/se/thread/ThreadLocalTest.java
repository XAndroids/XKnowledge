package com.java.xknowledge.se.thread;

/**
 * TheadLocal使用
 * 创建只能被同一个线程读写的变量，如果一段代码含有一个ThreadLocal变量的引用，即使两个线程同时执行这段代码，也无法
 * 访问对方的ThreadLocal变量
 * 参考：https://www.jianshu.com/p/74f1a883da50
 */
class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal;

    public static void main(String[] args) {
        threadLocal = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "初始化值";
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(new MyRunnable(), "线程" + i).start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "的threadLocal" + ",设置为" + name);
            threadLocal.set(name);//多个不同的线程可以同时访问一个threadLocald对象
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ":" + threadLocal.get());//但是每个线程通过threadLocal只能获取自己保存的值
        }
    }
}
