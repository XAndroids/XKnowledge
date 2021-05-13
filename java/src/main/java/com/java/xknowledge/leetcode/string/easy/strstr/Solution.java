package com.java.xknowledge.leetcode.string.easy.strstr;

/**
 * 28. 实现 strStr()
 * 链接：https://leetcode-cn.com/problems/implement-strstr/
 */
class Solution {
    public int strStr(String haystack, String needle) {
        //参数校验都不为null，且needle要小于haystack
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }

        //从haystack start开始遍历，如果首字母相同则从此节点开始j匹配needle
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                //遍历匹配子串
                while (j < needle.length()) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        break;
                    }
                    j++;
                }

                if (j == needle.length()) {
                    return i;
                } else {
                    i += j;
                }
            } else {
                i++;
            }
        }

        return -1;
    }
}
