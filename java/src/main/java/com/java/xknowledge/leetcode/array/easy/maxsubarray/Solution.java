package com.java.xknowledge.leetcode.array.easy.maxsubarray;

/**
 * 53. 最大子序和
 * 链接：https://leetcode-cn.com/problems/maximum-subarray/
 */
class Solution {
    //递推思想
    public int maxSubArray(int[] nums) {
        int sum = 0;
        //当前最大子序列和为maxSum
        int maxSum = Integer.MIN_VALUE;

        for (int num : nums) {
            if (sum >= 0) {
                //如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
                sum = sum + num;
            } else if (sum < 0) {
                //如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
                sum = num;
            }

            maxSum = Math.max(sum, maxSum);
        }

        return maxSum;
    }
}
