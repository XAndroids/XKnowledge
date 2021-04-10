package com.java.xknowledge.leetcode.other.isvalid;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 20. 有效的括号
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 */
public class Solution {
    public static boolean isValid(String s) {
        //1.参数合法性校验
        if (s == null || s.length() == 0) {
            return false;
        }

        //2.剪枝概念，只要字符的长度不是偶数，肯定不是有效括号
        int length = s.length();
        if (length % 2 == 1) {
            return false;
        }

        //3.逻辑上，值有有边的括号) ] }才需要匹配
        Map<Character, Character> bracketMap = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };

        //采用LinkedList实现栈，Stack继承Vector线程安全性能消耗
        Deque<Character> bracketStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character keyBracket = s.charAt(i);
            if (bracketMap.containsKey(keyBracket)) {
                //5.如果是右边括号，如果栈为null则fase，或者不匹配则立即返回false！！
                if (bracketStack.isEmpty() || !bracketStack.peek().equals(bracketMap.get(keyBracket))) {
                    return false;
                } else {
                    //6.如果匹配，则出栈
                    bracketStack.pop();
                }
            } else {
                //4.如果是左边的括号( [ { 直接入栈
                bracketStack.push(keyBracket);
            }
        }

        //7.最终判断栈为空，就是符合要求括号
        return bracketStack.isEmpty();
    }
}
