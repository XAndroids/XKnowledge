package com.java.xknowledge.leetcode.array.easy.containsnearbyduplicate;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii/
 */
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //参数校验
        if (nums == null || k <= 0) {
            return false;
        }

        //遍历数组，保存已存在数据Map，
        Map<Integer, Integer> cacheMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //如果相等每次计算索引差值，如果>k return false
            if (cacheMap.containsKey(nums[i]) && (i - cacheMap.get(nums[i])) < k) {
                return true;
            }
            cacheMap.put(nums[i], i);
        }

        return false;
    }
}
