package com.java.xknowledge.se.thread.atomic;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Atomic更新数组类型
 * 运行：
 * atomicIntegerArray = [1, 5]
 * atomicIntegerArray = [1, 1]
 * 参考：《享学2《Java 筑基-06并发基础知识补全和CAS基本原理》》
 */
class AtomicArrayTest {
    static int[] sourceIntArray = new int[]{1, 2};
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(sourceIntArray);

    public static void main(String[] args) {
        //addAndGet：以原子的方式将输入值，与数组中索引i的元素相加
        atomicIntegerArray.addAndGet(1, 3);
        System.out.println("atomicIntegerArray = " + atomicIntegerArray.toString());

        //compareAndSet：如果当前值等于预期值，则以原子的方式将数组位置i的元素设置成update值
        atomicIntegerArray.compareAndSet(1, 5, 1);
        System.out.println("atomicIntegerArray = " + atomicIntegerArray.toString());

        //注意：数组value通过构造方法传递进入，然后AtomicIntegerArray复制一份，当对AtomicIntegerArray内部数
        //组元素修改，不影响传入的数组
        //构造函数源码：this.array = array.clone();
        System.out.println("sourceIntArray = " + Arrays.toString(sourceIntArray));
    }
}
