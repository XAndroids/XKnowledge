package com.java.xknowledge.arithmetic.sort;

/**
 * 冒泡排序
 */
class BubbleSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }

        //每次循环找出第i个大的数，放在数组末尾倒数第i个，到array.length个找出后即比较结束
        for (int i = 0; i < array.length; i++) {
            //从数组0开始，依次比较相邻元素，到array.length - 1 - i为比较完毕未排序数字
            for (int j = 0; j < array.length - 1 - i; j++) {
                //如果大小不符合交换
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
