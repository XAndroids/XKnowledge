package com.java.xknowledge.leetcode.array.easy.findcontinuoussequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 */
class Solution {
    /**
     * 暴力破解法
     */
    public static int[][] findContinuousSequence1(int target) {
        List<int[]> result = new ArrayList<>();//使用List<int[]>保存结果

        //从每个数字开始，遍历所有组合
        for (int i = 1; i <= target / 2; i++) {//和为target连续正数，起始数字不会超过target/2
            int sum = 0;
            for (int j = i; j < target; j++) {//和为target连续正数，起始数字不会超过target
                sum = sum + j;
                if (sum > target) {
                    //如果不满足需求，退出再次遍历
                    break;
                } else if (sum == target) {
                    //如果满足，则保存从i到j的连续正数，继续后续遍历
                    int[] subResult = new int[j - i + 1];
                    for (int k = i; k <= j; k++) {
                        subResult[k - i] = k;
                    }
                    result.add(subResult);
                    break;
                }
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     * 滑动窗口解法
     * 左右两个指针控制窗口大小：
     * 如果sum < target，则right右移动；
     * 如果sum > target，则left右移动；
     * 如果sum == target，则保存left到right的数组，left右移动；
     */
    public static int[][] findContinuousSequence2(int target) {
        List<int[]> result = new ArrayList<>();

        //初始left 1，right 2，sum = left + right窗口
        int left = 1;
        int right = 2;
        int sum = left + right;
        int limit = target / 2;

        while (left <= limit) {//窗口左边界不超过target/2
            if (sum < target) {//如果sum < target，则right右移动
                right++;
                sum += right;
            } else {
                if (sum == target) {//如果sum == target，则保存数组结果
                    int[] subResult = new int[right - left + 1];
                    for (int k = left; k <= right; k++) {
                        subResult[k - left] = k;
                    }
                    result.add(subResult);
                }

                sum -= left;//如果sum >= target，则left右移动
                left++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(findContinuousSequence2(15)));
    }
}
