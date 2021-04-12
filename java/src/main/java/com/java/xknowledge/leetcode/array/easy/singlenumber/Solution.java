package com.java.xknowledge.leetcode.array.easy.singlenumber;

import java.util.HashSet;
import java.util.Set;

/**
 * 136. 只出现一次的数字
 * 链接：https://leetcode-cn.com/problems/single-number/
 */
class Solution {
    /**
     * HashSet不重复特性
     */
    public static int singleNumber(int[] nums) {
        Set<Integer> singleSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!singleSet.add(nums[i])) {
                singleSet.remove(i);
            }
        }

        return singleSet.iterator().next();
    }

    /**
     * 抑或运算，取重复
     */
    public static int singleNumber2(int[] nums) {
        int singleNumber = 0;
        for (int i = 0; i < nums.length; i++) {
            singleNumber = singleNumber ^ nums[i];//抑或运算，去重复
        }
        return singleNumber;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber2(new int[]{2, 2, 1, 4, 4}));
    }
}
