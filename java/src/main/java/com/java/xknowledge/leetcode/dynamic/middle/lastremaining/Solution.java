package com.java.xknowledge.leetcode.dynamic.middle.lastremaining;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 */
class Solution {
    public int lastRemaining(int n, int m) {
        int position = 0;//最后一轮，剩下数字的位置
        for (int i = 2; i <= n; i++) {
            position = (position + m) % i;//反向递推公式，向前移动m位置，将
        }
        return position;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 9};
        //遍历索引
        int reverIndex = 0;
        //叫号索引
        int index = 1;
        //选中集合
        Set<Integer> selected = new HashSet<>();

        //如果所有数字都没有选中，继续遍历
        while (selected.size() < nums.length) {
            //通过求余计算当前索引
            int numIndex = reverIndex % nums.length;

            //如果当前数字已经被选中过，则忽略往后执行
            if (!selected.contains(nums[numIndex])) {
                //如果当前数字未被选中过，则判断是否是3，是则添加选中，重新从1开始叫号
                if (index == 3) {
                    selected.add(nums[numIndex]);
                    System.out.println(nums[numIndex]);
                    index = 1;
                } else {
                    //如果不是3号，则叫下一个号
                    index++;
                }
            }

            //循环遍历数组索引增加
            reverIndex++;
        }
    }
}
