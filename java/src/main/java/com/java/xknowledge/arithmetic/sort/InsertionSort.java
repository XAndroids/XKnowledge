package com.java.xknowledge.arithmetic.sort;

/**
 * 插入排序
 */
class InsertionSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        int insertValue;//当前待排序数据，待插入到以排序元素之间指定位置
        for (int i = 0; i < array.length - 1; i++) {
            int sortIndex = i;//已排序数据的索引
            insertValue = array[i + 1];//本次循环待排序元素为已排序元素下一个array[i+1]
            //遍历已排序数据，如果已排序数据array[sortIndex]>insertValue，则比较数据向后移动空出"插入位置"
            while (sortIndex >= 0 && insertValue < array[sortIndex]) {
                array[sortIndex + 1] = array[sortIndex];
                sortIndex--;
            }
            //如果不满足，则找到插入位置sortIndex，将待插入的值insertValue插入sortIndex+1位置
            array[sortIndex + 1] = insertValue;
        }
        return array;
    }
}
