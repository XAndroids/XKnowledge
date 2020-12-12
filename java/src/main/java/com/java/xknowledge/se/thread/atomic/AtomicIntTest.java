package com.java.xknowledge.se.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic更新基本类型类使用
 * 运行：
 * getAndDecrement = 3
 * compareAndSet = true
 * getAndIncrement = 4
 * getAndSet = 5
 * 参考：享学2《Java 筑基-06并发基础知识补全和CAS基本原理》
 */
class AtomicIntTest {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        //addAndGet:以原子方式将输入的数值和实例中的值相加，并返回结果
        System.out.println("getAndDecrement = " + atomicInteger.addAndGet(2));
        //compareAndSet：如果输入的数值等于预期值，则以原子的方式将该值设置为输入的值
        System.out.println("compareAndSet = " + atomicInteger.compareAndSet(3, 4));
        //getAndIncrement：以原子的方式将当前值加1，并返回自增前的值
        System.out.println("getAndIncrement = " + atomicInteger.getAndIncrement());
        //getAndSet：以原子的方式设置为新值，并返回旧值
        System.out.println("getAndSet = " + atomicInteger.getAndSet(6));
    }
}
