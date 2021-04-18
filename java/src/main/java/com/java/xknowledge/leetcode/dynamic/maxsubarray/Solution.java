package com.java.xknowledge.leetcode.dynamic.maxsubarray;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * 题解：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/mian-shi-ti-42-lian-xu-zi-shu-zu-de-zui-da-he-do-2/
 */
class Solution {
    /**
     * 动态规划方案
     * 状态定义：dp[i]是nums[i]结尾的连续数组最大和；
     * 转义方程：dp[i] = dp[i-1] + nums[i]，dp[i-1]>0/dp[i] = nums[i]，dp[i-1] <= 0
     * 初始化状态：dp[0] = nums[0];
     * 返回值：dp数组中最大值
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];//状态定义dp为nums[i]结尾连续数组最大和
        dp[0] = nums[0];//初始状态dp[0] = nums[0]
        int maxSum = nums[0];//最大和默认nums[0]

        //遍历nums数组，通过状态转义方程，计算每个nums[i]结尾的最大和
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];//计算每个nums[i]结尾的最大和
            maxSum = Math.max(dp[i], maxSum);//计算当前最大和maxSum和nums[i]的最大值
        }

        return maxSum;
    }
}
