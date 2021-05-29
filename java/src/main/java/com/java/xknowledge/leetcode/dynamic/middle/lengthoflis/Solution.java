package com.java.xknowledge.leetcode.dynamic.middle.lengthoflis;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
class Solution {
    //动态递归方案
    public int lengthOfLIS(int[] nums) {
        //参数校验
        if (nums == null) return 0;
        if (nums.length == 0 || nums.length == 1) return nums.length;

        //初始化dp[]数组，dp[i]代表前i个元素最大递增子序列长度，默认都是1
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        //最长递增子序列长度
        int maxResult = 0;
        //递推计算
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //如果nums[i]>nums[j]时，则为递增，最长子序列d[j] + 1。但可能和其它的nums[j]更大故去Math.max
                //(dp[i],dp[j]+1)
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                //否则为递减什么都不错！！！
                maxResult = Math.max(maxResult, dp[i]);
            }
        }

        return maxResult;
    }

    //暴力破解法！！！，次算法不行。如case[0,1,0,3,2,3]，按此算法结果是3，但是0,1,2,3结果是4！！！
    public int lengthOfLIS2(int[] nums) {
//        //参数校验
//        if (nums == null) return 0;
//        if (nums.length == 0 || nums.length == 1) return nums.length;
//
//        //从i开始，遍历i+1~nums.length根据规则，计算最大长度
//        int maxResult = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int maxNum = nums[i];
//            int maxLen = 1;
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[j] > maxNum) {
//                    maxLen++;
//                    maxNum = nums[j];
//                }
//            }
//
//            maxResult = Math.max(maxResult, maxLen);
//        }
//
//        return maxResult;
    }
}
