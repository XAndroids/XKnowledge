package com.java.xknowledge.leetcode.string.easy.countletters;

/**
 * 1180. 统计只含单一字母的子串
 * 链接：https://leetcode-cn.com/problems/count-substrings-with-only-one-distinct-letter/
 */
class Solution {
    public int countLetters(String S) {
        //参数校验
        if (S == null || S.length() == 0) return 0;

        int sameCount = 1;//相同字符字符串长度
        int childCount = 0;//单一字母子串个数

        //遍历计算最长单一字母字段，再计算单一字母子串个数
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i - 1) == S.charAt(i)) {//如果字符相同，则统计+1
                sameCount++;
            } else {
                childCount += (sameCount * (sameCount + 1)) / 2;//如果字符不同，则计算子串个数并统计
                sameCount = 1;//重新开始统计相同字符长度
            }
        }

        //最后如果到末尾也未重复，再次计算
        childCount += (sameCount * (sameCount + 1)) / 2;

        return childCount;
    }
}
