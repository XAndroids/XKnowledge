package com.java.xknowledge.leetcode.array.medium.nextpermutation;

/**
 * 31. 下一个排列
 * 题目：https://leetcode-cn.com/problems/next-permutation/
 * 题解：
 * （思路）https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 * （代码）https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-powcai/
 */
class Solution {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        //从左往右查找第一个降序位置索引
        int exchangeFont = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) {
                continue;
            }
            exchangeFont = i;
            break;
        }

        //T如果没有降序全部是升序，则为最大排列，逆序为最小返回
        if (exchangeFont == -1) {
            reversal(nums, 0, nums.length - 1);
            return;
        }

        //从左到右找到比降序点数字最小的元素，并进行交换
        int exchangeEnd = -1;
        for (int i = nums.length - 1; i > exchangeFont; i--) {
            if (nums[i] > nums[exchangeFont]) {
                exchangeEnd = i;
                break;
            }
        }

        //找到的两个位置索引元素交换
        exchange(nums, exchangeFont, exchangeEnd);

        //将交换节点后的元素进行升序排序
        reversal(nums, exchangeFont + 1, nums.length - 1);
    }

    /**
     * 交换数组指定位置元素
     */
    private static void exchange(int[] nums, int exchangeFont, int exchangeEnd) {
        int temp = nums[exchangeFont];
        nums[exchangeFont] = nums[exchangeEnd];
        nums[exchangeEnd] = temp;
    }

    /**
     * 反转数组start和end区间的元素
     */
    private static void reversal(int[] nums, int start, int end) {
        //务必交换元素进行排序
        while (start < end) {
            exchange(nums, start++, end--);
        }
    }

    public static void main(String[] args) {

    }
}
