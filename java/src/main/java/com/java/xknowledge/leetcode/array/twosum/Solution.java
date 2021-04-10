package com.java.xknowledge.leetcode.array.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 链接：https://leetcode-cn.com/problems/two-sum/
 */
class Solution {
    /**
     * 暴力破解法：遍历所有数字组合，确认相加之和是否=target
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                //暴力遍历所有数据组合，判断是否满足条件
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[0];
    }

    /**
     * 哈希解法：辅助hashMap保存遍历过的数据匹配的数据，和匹配数据原数据的索引
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> checkMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (checkMap.containsKey(nums[i])) {
                //如果当前遍历的数据是寻找目标，则返回匹配数据索引和当前数据索引i
                return new int[]{checkMap.get(nums[i]), i};
            } else {
                //如果当前遍历的数据不是寻找目标，则保存要寻找目标和当前数据索引
                checkMap.put(target - nums[i], i);
            }
        }

        //否则返回空数组
        return new int[0];
    }
}
