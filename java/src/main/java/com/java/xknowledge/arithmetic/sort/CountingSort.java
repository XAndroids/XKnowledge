package com.java.xknowledge.arithmetic.sort;

import java.util.Arrays;

/**
 * 计数排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class CountingSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        //寻找数组中的最大值，最小值
        int min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        //获得计数数组容量
        int[] countArray = new int[max - min + 1];
        Arrays.fill(countArray, 0);

        //bias：偏移量，用以定位原始数组每个元素在计数数组中的下标位置
        int bias = -min;
        //遍历整个原始数组，将原始数组中每个元素值转化为计数数组下标
        //并将计数数组下标对应的元素值大小进行累加
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] + bias]++;
        }

        //访问计数数组时的下标计数器
        int i = 0;
        //访问原始数组时的下标计数器
        int index = 0;
        while (index < array.length) {
            //只要计数数组中当前下标元素的值不为0，就将计数数组中的元素转换后，重新写回原始数组
            if (countArray[i] != 0) {
                array[index] = i - bias;
                countArray[i]--;
                index++;
            } else {
                i++;
            }
        }
        return array;
    }
}
