package com.java.xknowledge.arithmetic.sort;

/**
 * 快速排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class QuickSort {
    public static int[] sort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) {
            return null;
        }

        //数组分割成两部分，从哪儿分区指示器
        int zooeIndex = partition(array,start,end);
        return array;
    }

    private static int partition(int[] array, int start, int end) {
        return 0;
    }
}
