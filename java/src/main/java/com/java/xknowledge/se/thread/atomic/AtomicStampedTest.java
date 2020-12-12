package com.java.xknowledge.se.thread.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference实践：带版本，解决ABA问题
 * 运行：
 * oldStamp = 0,oldReference = Mark
 * Thread-0,当前变量 = Mark,当前版本:0,compareAndSet = true
 * Thread-1,当前变量 = Mark,当前版本:0,compareAndSet = false
 * oldStamp = 0,oldReference = Mark
 * 参考：享学2《Java 筑基-06并发基础知识补全和CAS基本原理》
 */
class AtomicStampedTest {
    //初始化引用"Mark"，初始版本为0
    static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("Mark", 0);

    public static void main(String[] args) throws InterruptedException {
        //获取当前的版本（旧版本）
        final int oldStamp = atomicStampedReference.getStamp();
        final String oldReference = atomicStampedReference.getReference();
        System.out.println("oldStamp = " + oldStamp + ",oldReference = " + oldReference);
        Thread rightThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ",当前变量 = " + atomicStampedReference.getReference() +
                        ",当前版本:" + atomicStampedReference.getStamp() + ",compareAndSet = " + atomicStampedReference
                        .compareAndSet(oldReference, oldReference + "java", oldStamp, oldStamp + 1));
            }
        });
        Thread leftThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ",当前变量 = " + atomicStampedReference.getReference() +
                        ",当前版本:" + atomicStampedReference.getStamp() + ",compareAndSet = " + atomicStampedReference
                        .compareAndSet(oldReference, oldReference + "", oldStamp, oldStamp + 1));
            }
        });
        rightThread.join();//等待rithtThread执行完毕
        rightThread.start();
        leftThread.join();//等待leftThread执行完毕
        leftThread.start();

        System.out.println("oldStamp = " + oldStamp + ",oldReference = " + oldReference);
    }
}
