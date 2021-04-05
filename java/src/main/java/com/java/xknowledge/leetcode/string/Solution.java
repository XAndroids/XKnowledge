package com.java.xknowledge.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;//滑动窗口左边索引
        int maxLength = 0;//不重复子串最大长度
        Map<Character, Integer> characterMap = new HashMap<>();//key判断重复之外，value可以记录字符索引
        for (int right = 0; right <= s.length() - 1; right++) {//循环索引right为滑动窗口右边索引
            if (characterMap.containsKey(s.charAt(right))) {
                //当"abcba"，characterMap为"a-0/b-1/c-2"时，此时b-3包含，left=characterMap.get(s.
                //charAt(right)) + 1=2
                //因为characterMap没有移除a-0，下一步a-4时包含(但并不是重复了)，left=left不变；
                left = Math.max(left, characterMap.get(s.charAt(right)) + 1);
            }
            characterMap.put(s.charAt(right), right);//虽然重复但继续添加，覆盖原来重复字符信息
            maxLength = Math.max(maxLength, right - left + 1);//每次窗口变动，计算最新的窗口大小取最大值
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aab"));
    }
}
