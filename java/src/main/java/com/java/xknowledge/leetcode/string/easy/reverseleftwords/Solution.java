package com.java.xknowledge.leetcode.string.easy.reverseleftwords;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 */
class Solution {
    /**
     * 字符串拼接
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}
