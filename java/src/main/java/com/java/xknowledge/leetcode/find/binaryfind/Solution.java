package com.java.xknowledge.leetcode.find.binaryfind;

/**
 * 704. 二分查找
 * 链接：https://leetcode-cn.com/problems/binary-search/
 */
class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {//收尾不交叉
            int mid = (start + end) / 2;//取整
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;//判断相同必须 return 返回，否则while循环一直无法退出！！！
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
