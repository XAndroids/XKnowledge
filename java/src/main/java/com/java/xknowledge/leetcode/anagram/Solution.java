package com.java.xknowledge.leetcode.anagram;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * 参考：https://leetcode-cn.com/problems/valid-anagram/
 */
class Solution {
    /**
     * 思路：字母异位置即字符乱序排序不一样。则先进行字符排序，在判断是否相等即可
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {//提前校验长度不同，则肯定不是
            return false;
        }

        char[] aChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(tChars);
        String sString = String.copyValueOf(aChars);
        String tString = String.copyValueOf(tChars);
        return sString.equals(tString);
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
