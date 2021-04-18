package com.java.xknowledge.leetcode.find.search;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 题目：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * 题解：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/solution/mian-shi-ti-53-i-zai-pai-xu-shu-zu-zhong-cha-zha-5/
 */
class Solution {
    public int search(int[] nums, int target) {
        //二分查找区间右边界
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            //如果nums[mid] == target，则start = mid + 1查找右边界，每次移动1位直到正好移出右边界
            //此后end会一直向start=右边边界靠拢直到不满足start<=end，此时right = start
            if(nums[mid] <= target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        int right = start;

        //如果数组中没有target，提前返回
        // if(end >= 0 && nums[end] != target) return 0;

        //二分查找区间左边界
        start = 0;
        end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            //同理右边界
            if(nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        int left = end;

        //既然有右边界了，就一定会有左边界，不用校验数组无target了

        return right - left - 1;
    }
}
