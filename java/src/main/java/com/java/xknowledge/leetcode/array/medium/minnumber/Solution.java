package com.java.xknowledge.leetcode.array.medium.minnumber;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 */
class Solution {
    public String minNumber(int[] nums) {
        //参数校验
        if (nums == null) return null;

        //快速自定义排序数组
        quickSort(nums, 0, nums.length - 1);

        //将排序好的数组拼接成字符串
        StringBuilder builder = new StringBuilder();
        for (int num : nums) {
            builder.append(num);
        }
        return builder.toString();
    }

    private void quickSort(int[] nums, int start, int end) {
        int partitionIndex = partition(nums, start, end);

        if (partitionIndex > start) {
            quickSort(nums, 0, partitionIndex - 1);
        }

        if (partitionIndex < end) {
            quickSort(nums, partitionIndex + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int partitionIndex = (int) (start + Math.random() * (end - start + 1));
        swap(nums, partitionIndex, end);
        int zoomIndex = start - 1;
        for (int i = start; i < end; i++) {
            if (compare(nums[i], nums[end])) {
                zoomIndex++;
                if (zoomIndex < i) {
                    swap(nums, zoomIndex, i);
                }
            }
        }
        return zoomIndex;
    }

    //true=小
    private boolean compare(int num1, int num2) {
        int num1num2 = Integer.parseInt(num2 + String.valueOf(num1));
        int num2num1 = Integer.parseInt(num1 + String.valueOf(num2));
        return num1num2 <= num2num1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
