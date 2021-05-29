package com.java.xknowledge.leetcode.dynamic.middle.lastremaining;

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
}
