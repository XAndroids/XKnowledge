package com.java.xknowledge.arithmetic.sort;

/**
 * 排序测试类
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class SortTest {
    public static void main(String[] args) {
        PrintArray.print(PrintArray.SRC);
        System.out.println("===================Bubble Sort=========================");
        PrintArray.print(BubbleSort.sort(PrintArray.SRC));
        System.out.println("===================Choice Sort=========================");
        PrintArray.print(ChoiceSort.sort(PrintArray.SRC));
    }
}
