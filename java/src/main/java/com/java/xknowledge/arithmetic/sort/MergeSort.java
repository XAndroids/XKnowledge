package com.java.xknowledge.arithmetic.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 参考：《享学1：14、排序算法的实现总结及性能对比，应用场景》
 */
class MergeSort {
    public static int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        //切分数组，然后递归左右数组进行排序后合并
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(sort(left), sort(right));
    }

    /**
     * 归并排序-将两段排序好的数组结合成一个排序数组
     */
    private static int[] merge(int[] left, int[] right) {
        //创建merge结果数组
        int[] result = new int[left.length + right.length];
        //遍历左右数组，将数据进行合并
        for (int index = 0, leftIndex = 0, rightIndex = 0; index < result.length; index++) {
            if (leftIndex >= left.length) { //如果左边数组取完了，完全取右边数组的值即可
                result[index] = right[rightIndex++];
            } else if (rightIndex >= right.length) {//如果右边的值取完了，完全取左边数组的值即可；
                result[index] = left[leftIndex++];
            } else if (left[leftIndex] > right[rightIndex]) {//如果左边数组的元素大于右边数组的元素，则取右边数组的值
                result[index] = right[rightIndex++];
            } else {//如果左边数组的元素小于右边数组元素，则取左边数组的值
                result[index] = left[leftIndex++];
            }
        }
        return result;
    }
}
