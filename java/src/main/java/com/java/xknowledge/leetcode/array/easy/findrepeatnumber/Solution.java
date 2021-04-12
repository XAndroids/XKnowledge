package com.java.xknowledge.leetcode.array.easy.findrepeatnumber;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 */
class Solution {
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> noRepeatSet = new HashSet<>();
        for (int i = 0; i <= nums.length - 1; i++) {
            if (!noRepeatSet.add(nums[i])) {//判断重复性，使用HashSet的不可重复特性
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findRepeatNumber(new int[]{3, 1, 2, 3}));
    }
}
