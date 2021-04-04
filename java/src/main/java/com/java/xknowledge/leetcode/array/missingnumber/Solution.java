package com.java.xknowledge.leetcode.array.missingnumber;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 */
class Solution {
    public static int missingNumber(int[] nums) {
        int start = 0;
        int end = nums.length - 1;//初始化查找前后索引

        while (start <= end) {//如果前后存在交叉则退出
            int mid = (start + end) / 2;//计算mid索引，取整数
            if (nums[mid] == mid) {//如果在右子数组，则start=mid + 1
                start = mid + 1;
            } else {//如果在左数组，end = end -1
                end = mid - 1;
            }
        }
        return start;//返回start
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{0, 1, 2, 4, 5, 6, 7, 8, 9, 10}));
    }
}
