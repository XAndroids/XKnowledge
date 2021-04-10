package com.java.xknowledge.leetcode.anagram;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词
 * 链接：https://leetcode-cn.com/problems/valid-anagram/
 */
class Solution {
    /**
     * 思路：字母异位置即字符乱序排序不一样。则先进行字符排序，在判断是否相等即可
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {//提前校验长度不同，则肯定不是
            return false;
        }

        char[] aChars = s.toCharArray();//比较字符串转换成字符数组
        char[] tChars = t.toCharArray();
        Arrays.sort(aChars);//对子字符数组进行排序
        Arrays.sort(tChars);
        String sString = String.copyValueOf(aChars);//再次转换成字符串
        String tString = String.copyValueOf(tChars);
        return sString.equals(tString);//排序后字符串应该相等
    }

    /**
     * 思路：字母异位即字符种类和个数相同，对一个字符串进行统计，用另一个字符串验证
     */
    public static boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {//1.先决条件或参数校验
            return false;
        }

        //2.顺序元素，如字母计数可使用数组，数组索引对应字符编码
        //3.固定大小数据，声明固定大小集合
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }

        //4.不需要两个字符串都统计，计算一个字符串，用另一个验证即可
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            counts[index]--;
            if (counts[index] < 0) {
                return false;
            }
        }

        return true;
    }
}
