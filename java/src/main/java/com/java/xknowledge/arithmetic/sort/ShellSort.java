package com.java.xknowledge.arithmetic.sort;

/**
 * 希尔排序
 */
class ShellSort {
    public static int[] sort(int[] array) {
        int len = array.length;
        if (len == 0) {
            return array;
        }

        int insertValue, gap = len / 2;//以len/2为希尔排序初始分组间隔，对分组元素进行插入排序
        while (gap > 0) {
            //根据gap间隔，对分组元素进行插入排序
            for (int i = gap; i < len; i++) {
                insertValue = array[i];
                int sortIndex = i - gap;
                while (sortIndex >= 0 && array[sortIndex] > insertValue) {
                    array[sortIndex + gap] = array[sortIndex];
                    sortIndex -= gap;
                }

                array[sortIndex + gap] = insertValue;
            }

            gap /= 2;//插入排序完成后，缩小分组间隔，继续进行插入排序，直到不需要分组则排除完毕
        }
        return array;
    }
}
