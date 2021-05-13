package com.java.xknowledge.leetcode.string.easy.longestcommonprefix;

/**
 * 14. 最长公共前缀
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix/
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int checkIndex = 0;
        boolean checkContinue = true;

        while (checkContinue) {
            char indexChar = strs[0].charAt(checkIndex);
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].charAt(checkIndex) != indexChar) {
                    checkContinue = false;
                    break;
                }
            }
            checkIndex++;
        }
        return strs[0].substring(0, checkIndex);
    }
}
