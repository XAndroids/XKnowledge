package com.java.xknowledge.leetcode.string.medium.lengthoflongestsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int maxChildLength = 0;
        Map<Character, Integer> helperMap = new HashMap<>();

        int left = 0;
        int right = 1;
        while (right < s.length() - 1) {
            char rightChar = s.charAt(right);
            if (helperMap.containsKey(rightChar)) {
                left = Math.max(left, helperMap.get(s.charAt(right)));
                left++;
            }

            helperMap.put(rightChar, right);
            maxChildLength = Math.max(maxChildLength, right - left + 1);
            right++;
        }

        return maxChildLength;
    }
}
