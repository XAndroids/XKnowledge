package com.java.xknowledge.arithmetic.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 排序测试类
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class SortTest {
    public static void main(String[] args) {
        PrintArray.print(PrintArray.SRC);
//        System.out.println("===================Bubble Sort=========================");
//        PrintArray.print(BubbleSort.sort(PrintArray.SRC));
//        System.out.println("===================Choice Sort=========================");
//        PrintArray.print(ChoiceSort.sort(PrintArray.SRC));
//        System.out.println("===================Insertion Sort=========================");
//        PrintArray.print(InsertionSort.sort(PrintArray.SRC));
//        System.out.println("===================Shell Sort=========================");
//        PrintArray.print(ShellSort.sort(PrintArray.SRC));
//        System.out.println("===================Merge Sort=========================");
//        PrintArray.print(MergeSort.sort(PrintArray.SRC));
//        System.out.println("===================Rasix Sort=========================");
//        PrintArray.print(RadixSort.sort(PrintArray.SRC));
//        System.out.println("===================Bucket Sort=========================");
//        ArrayList<Integer> array = new ArrayList<>();
//        array.add(86);
//        array.add(11);
//        array.add(77);
//        array.add(23);
//        array.add(32);
//        array.add(45);
//        array.add(58);
//        array.add(63);
//        array.add(93);
//        array.add(4);
//        array.add(37);
//        array.add(22);
//        PrintArray.printObject(BucketSort.sort(array, 2));
//        System.out.println("===================Counting Sort=========================");
//        PrintArray.print(CountingSort.sort(PrintArray.SRC));
//        System.out.println("===================Heap Sort=========================");
//        PrintArray.print(HeapSort.sort(PrintArray.SRC));

        System.out.println("===================Quick Sort=========================");
        PrintArray.print(Objects.requireNonNull(QuickSort.sort(PrintArray.SRC, 0, PrintArray.SRC.length - 1)));
    }
}
