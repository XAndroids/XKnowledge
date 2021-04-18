package com.java.xknowledge.leetcode.string.easy.firstuniqchar;

import java.util.HashMap;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 题目：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */
class Solution {
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }

        //countMap记录出现次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            //countMap.getOrDefault不用判断是否包含
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        //遍历s，即按照出现次序返回，不用使用LinkedHashMap
        for (int i = 0; i < s.length(); i++) {
            if (countMap.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }

        return ' ';
    }
}
