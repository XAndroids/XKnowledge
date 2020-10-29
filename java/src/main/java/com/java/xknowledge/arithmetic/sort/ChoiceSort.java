package com.java.xknowledge.arithmetic.sort;

/**
 * 选择排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class ChoiceSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        //每次循环，找出最大(小)的值的索引minIndex
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            //从未排序的第i个开始到array.length，查找到最大(小)值minIndex
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            //然后将最小minIndex位置的数据，交换到数组的第i个位置
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }

        return array;
    }
}
