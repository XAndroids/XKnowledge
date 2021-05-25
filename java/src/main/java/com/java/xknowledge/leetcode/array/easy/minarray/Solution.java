package com.java.xknowledge.leetcode.array.easy.minarray;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 */
class Solution {
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        //二分查找法
        while (left < right) {
            int middle = (left + right) / 2;
            if (numbers[middle] < numbers[right]) {
                //numbers[middle] < numbers[right]，则x一定在[left,middle]中
                right = middle;
            } else if (numbers[middle] > numbers[right]) {
                //如果numbers[middle] > numbers[right]，则x一定在[middle+1,right]中
                left = middle + 1;
            } else {
                //如果numbers[middle]=numbers[right]，则无法判断旋转点x在哪个区间
                //但一定在[i,j-1]中，故执行j = j -1缩小范围
                right--;
            }
        }
        return numbers[left];
    }
}
