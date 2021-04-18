package com.java.xknowledge.leetcode.sort.easy.getleastnumbers;

import java.util.Arrays;

/**
 * 剑指 Offer 40. 最小的k个数
 * 题目：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 */
class Solution {
    /**
     * 插入排序解法
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        //将arr[i+1]的元素，插入arr[0~i]的有序数组中
        for (int i = 0; i < arr.length - 1; i++) {
            //获取待插入元素
            int insertValue = arr[i + 1];
            //默认的插入位置是i
            int insertIndex = i;
            //依次比较arr[i~0]中元素和插入元素大小，如果大则arr[i]元素后移继续查找插入位置
            while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //直到找到插入位置，将值插入
            arr[insertIndex + 1] = insertValue;
        }

        //返回最小的k个数子数组
        return Arrays.copyOfRange(arr, 0, k);
    }

    /**
     * 快速排序法
     */
    public static int[] getLeastNumbers2(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        //返回最小的k个数子数组
        return Arrays.copyOfRange(arr, 0, k);
    }

    private static void quickSort(int[] arr, int start, int end) {
        //参数校验
        if (arr == null || start < 0 || end >= arr.length || start > end) {
            return;
        }

        //分区数组，返回分区完的分区指示器
        int zoneIndex = partition(arr, start, end);
        //对分区指示器左边进行递归快速排序
        if (zoneIndex > start) {
            quickSort(arr, start, zoneIndex - 1);
        }
        //对分区指示器右边进行递归快速排序
        if (zoneIndex < end) {
            quickSort(arr, zoneIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        //随机产生基准元素
        int pivot = (int) (start + Math.random() * (end - start + 1));
        //将基准元素和尾部元素交换
        swap(arr, pivot, end);
        //默认分区指示器为start-1
        int zoonIndex = start - 1;

        //遍历数组所有元素
        for (int i = start; i <= end; i++) {
            //如果小则分区指示器右移动
            if (arr[i] <= arr[end]) {
                zoonIndex++;
                //如果分区指示器比遍历索引小，则说明有较大元素在后面，则交换元素
                if (i > zoonIndex) {
                    swap(arr, zoonIndex, i);
                }
            }
        }

        return zoonIndex;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getLeastNumbers2(new int[]{3, 2, 1}, 2)));
    }
}
