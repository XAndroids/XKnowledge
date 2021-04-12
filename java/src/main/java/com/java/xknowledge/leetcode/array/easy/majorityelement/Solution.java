package com.java.xknowledge.leetcode.array.easy.majorityelement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 * 链接：https://leetcode-cn.com/problems/majority-element/
 */
class Solution {
    /**
     * 哈希法：遍历HashMap依次统计出现的次数，遍历统计结果返回>n/2的值
     */
    public int majorityElement(int[] nums) {
        //通过HashMap统计数字出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            if (!countMap.containsKey(num)) {
                countMap.put(num, 1);
            } else {
                countMap.put(num, countMap.get(num) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            //遍历统计结果，返回符合要求的数字
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);//题目指必定会有多数元素，故排序后返回nums.length/2位置元素必然是
        return nums[nums.length / 2];
    }
}
