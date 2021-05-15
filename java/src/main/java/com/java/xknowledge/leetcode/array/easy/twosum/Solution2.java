package com.java.xknowledge.leetcode.array.easy.twosum;

/**
 * 167. 两数之和 II - 输入有序数组
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 */
class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        //参数校验
        if (numbers == null || numbers.length <= 1) return null;

        //双指针，由于数组有序，则一大一小，根据条件右移动和左移动实现窗口增大或者缩小
        int left = 0;
        int right = numbers.length - 1;

        //“窗口”遍历，根据条件调整"窗口"
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1};
            }

        }

        return null;
    }
}
