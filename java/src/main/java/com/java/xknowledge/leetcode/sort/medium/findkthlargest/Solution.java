package com.java.xknowledge.leetcode.sort.medium.findkthlargest;

/**
 * 215. 数组中的第K个最大元素
 * 题目：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
class Solution {
    /**
     * 冒泡排序升序排序
     */
    public int findKthLargest1(int[] nums, int k) {
        //第1层循环表示，找出第i大的的元素，放在末尾第i个位置
        for (int i = 0; i < nums.length; i++) {
            //第2层循环，表示每次都从第0个开始，到第nums.length-i-1
            for (int j = 0; j < nums.length - i - 1; j++) {
                //交换相邻元素找到最大值
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        //返回数组第K大元素
        return nums[nums.length - k];
    }

    /**
     * 选择排序升序排序：避免重复的多次交换
     */
    public int findKthLargest2(int[] nums, int k) {
        //第一层循环，找出第i个最小元素放在第i个位置
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            //第二层循环，从i+1~num.length中找出第i小元素
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            //将最小值交换到第i个位置
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }

        //返回数组第K大元素
        return nums[nums.length - k];
    }


    /**
     * 插入排序升序排序
     */
    public int findKthLargest3(int[] nums, int k) {
        //第1层循环：将第i+1个元素，插入到0~i已排序的数组中
        for (int i = 0; i < nums.length - 1; i++) {
            int insertValue = nums[i + 1];//保存第i+1待插入元素的值
            int insertIndex = i;//初始化插入第i个位置
            //如果插入位置insertIndex的值大于待插入数值，则当前插入位置数字后移，继续判断前一个位置
            while (insertIndex >= 0 && nums[insertIndex] > insertValue) {
                nums[insertIndex + 1] = nums[insertIndex];
                insertIndex--;
            }

            //如果满足条件找到插入位置，则将插入值插入对应的位置上
            nums[insertIndex + 1] = insertValue;
        }

        //返回数组第K大元素
        return nums[nums.length - k];
    }

    /**
     * TODO 快速排序
     */
    public int findKthLargest4(int[] nums, int k) {
        return -1;
    }
}
