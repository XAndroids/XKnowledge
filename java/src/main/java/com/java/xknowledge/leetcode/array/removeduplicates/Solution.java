package com.java.xknowledge.leetcode.array.removeduplicates;

/**
 * 26. 删除有序数组中的重复项
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
class Solution {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int curIndex = 0;//当前判断值索引
        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] != nums[curIndex]) {//如果遍历值和当前值不同，则覆盖
                curIndex++;//增加索引存储新的当前值
                nums[curIndex] = nums[i];
            }
        }

        return curIndex + 1;
    }

    public static void main(String[] args) {

    }
}

