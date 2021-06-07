package com.java.xknowledge.se.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题模拟
 * 参考：https://juejin.cn/post/6844903558253379597
 */
class ABATest {
    //AtomicInteger无法避免ABA问题
    //运行：
    //操作线程：Thread[Thread-0,5,main]初始值 = 1
    //干扰线程：Thread[Thread-1,5,main]当前值 = 2
    //干扰线程：Thread[Thread-1,5,main]当前值 = 1
    //操作线程：Thread[Thread-0,5,main]CAS成功 = true
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main1(String[] args) {
        new Thread(() -> {
            System.out.println("操作线程：" + Thread.currentThread() + "初始值 = " + atomicInteger
                    .get());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //sleep后，被其它线程修改了
            boolean isCASSuccess = atomicInteger.compareAndSet(1, 2);
            System.out.println("操作线程：" + Thread.currentThread() + "CAS成功 = " + isCASSuccess);
        }).start();

        new Thread(() -> {
            //干扰线程增加后，又减少值，虽然值相同，但是其实修改了
            atomicInteger.getAndIncrement();
            System.out.println("干扰线程：" + Thread.currentThread() + "当前值 = " + atomicInteger
                    .get());
            atomicInteger.getAndDecrement();
            System.out.println("干扰线程：" + Thread.currentThread() + "当前值 = " + atomicInteger
                    .get());
        }).start();
    }

    //AtomicStampedReference可以避免ABA问题
    //运行：
    //操作线程：Thread[Thread-0,5,main]初始值 = 1初始版本=0
    //干扰线程：Thread[Thread-1,5,main]当前值 = 2当前版本=1
    //干扰线程：Thread[Thread-1,5,main]当前值 = 1当前版本=2
    //操作线程：Thread[Thread-0,5,main]CAS成功 = false
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(
            1, 0);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("操作线程：" + Thread.currentThread() + "初始值 = "
                    + atomicStampedReference.getReference() + "初始版本=" + atomicStampedReference
                    .getStamp());
            //提前获取获取值的版本
            int stamp = atomicStampedReference.getStamp();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //sleep后，被其它线程修改了，且版本更新了。如果还使用sleep前的版本就会CAS失败！！！！
            boolean isCASSuccess = atomicStampedReference.compareAndSet(1,
                    2, stamp, stamp + 1);
            System.out.println("操作线程：" + Thread.currentThread() + "CAS成功 = " + isCASSuccess);
        }).start();

        new Thread(() -> {
            //干扰线程增加后，又减少值，虽然值相同，但是版本已经变化了
            atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp()
                            + 1);
            System.out.println("干扰线程：" + Thread.currentThread() + "当前值 = " +
                    atomicStampedReference.getReference() + "当前版本=" + atomicStampedReference
                    .getStamp());

            atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp()
                            + 1);
            System.out.println("干扰线程：" + Thread.currentThread() + "当前值 = " +
                    atomicStampedReference.getReference() + "当前版本=" + atomicStampedReference
                    .getStamp());
        }).start();
    }
}
