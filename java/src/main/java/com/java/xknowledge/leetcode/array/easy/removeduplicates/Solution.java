package com.java.xknowledge.leetcode.array.easy.removeduplicates;

/**
 * 26. 删除有序数组中的重复项
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
class Solution {
    //题解1：双指针，判断和不重复节点不同，就交换否则不交换
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int curIndex = 0;//当前判断值索引
        for (int i = 0; i <= nums.length - 1; i++) {
            if (nums[i] != nums[curIndex]) {//如果遍历值和当前值不同，则覆盖
                curIndex++;//增加索引存储新的当前值
                nums[curIndex] = nums[i];
            }
        }

        return curIndex + 1;
    }

    //题解2：双指针，判断不重复节点
    public static int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = 1;//下一个交换位置
        int fast = 1;//当前校验位置

        while (fast < nums.length) {//falst未校验完所有数组
            //如果和当前非重复数组最大不同，则未新数字
            if (nums[slow - 1] != nums[fast]) {
                //交换保存新数字，更新下一个交换位置
                nums[slow] = nums[fast];
                slow++;
            }
            //更新下一个比较位置
            fast++;
        }

        //返回不重复数组的长度
        return slow;
    }
}

