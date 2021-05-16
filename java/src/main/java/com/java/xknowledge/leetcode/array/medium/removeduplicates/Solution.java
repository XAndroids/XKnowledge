package com.java.xknowledge.leetcode.array.medium.removeduplicates;

/**
 * 80. 删除有序数组中的重复项 II
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        //校验参数
        if (nums == null) return 0;
        if (nums.length <= 2) return nums.length;

        //快慢指针定义，因为最多2个可重复，故可以从第3个数字开始进行相关逻辑
        int slow = 2;
        int fast = 2;
        //while循环快指针未到末尾
        while (fast < nums.length) {
            //如果快指针数据!=慢指针-2数据，
            //如果：nums[slow-2] = nums[fast]，那么必定nums[slow-2] = nums[slow-1] = nums[fast]，则说明
            //已经有了2个以上相同数据，后移；
            //否则：nums[slow-2] != nums[fast]，要么nums[fast]要么1个，要么2个，但绝对不可能是2个以上！故交换
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }
}
