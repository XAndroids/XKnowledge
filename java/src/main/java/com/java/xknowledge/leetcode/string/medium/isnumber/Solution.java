package com.java.xknowledge.leetcode.string.medium.isnumber;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 20. 表示数值的字符串-中等
 * 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 */
class Solution {
    //状态转义Map，states[0]为0状态的可能下一个状态，key表示下一个字符，value表示对应的下一个状态
    Map[] states = {
            new HashMap<Character, Integer>() {{
                put(' ', 0);
                put('s', 1);
                put('d', 2);
                put('.', 4);
            }},
            new HashMap<Character, Integer>() {{
                put('d', 2);
                put('.', 4);
            }},
            new HashMap<Character, Integer>() {{
                put('d', 2);
                put('.', 3);
                put('e', 5);
                put(' ', 8);
            }},
            new HashMap<Character, Integer>() {{
                put('d', 3);
                put('e', 5);
                put(' ', 8);
            }},
            new HashMap<Character, Integer>() {{
                put('d', 3);
            }},
            new HashMap<Character, Integer>() {{
                put('s', 6);
                put('d', 7);
            }},
            new HashMap<Character, Integer>() {{
                put('d', 7);
            }},
            new HashMap<Character, Integer>() {{
                put('d', 7);
                put(' ', 8);
            }},
            new HashMap<Character, Integer>() {{
                put(' ', 8);
            }},
    };

    public boolean isNumber(String s) {
        //参数校验
        if (s == null || s.length() == 0) return false;

        //当前状态
        int p = 0;
        //当前类型
        char t;

        //遍历字符串所有字符
        for (char c : s.toCharArray()) {
            //根据字符类型，获取当前类型
            if (c >= '0' && c <= '9') {
                t = 'd';
            } else if (c == '+' || c == '-') {
                t = 's';
            } else if (c == 'e' || c == 'E') {
                t = 'e';
            } else if (c == '.' || c == ' ') {
                t = c;
            } else {
                t = '?';
            }

            //根据当前状态p，判断下一个字符是否合法，如果不合法返回false
            if (!states[p].containsKey(t)) {
                return false;
            }

            //如果合法更新当前状态，继续进行后续校验
            p = (int) states[p].get(t);
        }

        //如果校验完毕，p为2/3/7/8，则为合法的数值字符串
        return p == 2 || p == 3 || p == 7 || p == 8;
    }
}
