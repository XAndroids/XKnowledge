package com.java.xknowledge.leetcode.string.medium.hasallcodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
 * 链接：https://leetcode-cn.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 */
class Solution {
    public static void main(String[] args) {
        hasAllCodes("0000000001011100", 4);
    }

    public static boolean hasAllCodes(String s, int k) {
        //参数校验
        if (s == null || k <= 0 || k > s.length()) {
            return false;
        }

        List<String> kSubList = new ArrayList<>((int) Math.pow(2, k));
        //递归计算长度为k二进制子串
        makeKSub(k, new StringBuilder(), kSubList);
        System.out.println(kSubList.toString());

        //判断子串结果是否都在s中
        for (int i = 0; i < kSubList.size(); i++) {
            if (!s.contains(kSubList.get(i))) {
                return false;
            }
        }

        return true;
    }

    private static void makeKSub(int k, StringBuilder path, List<String> kSubList) {
        if (k == 0) {
            kSubList.add(path.toString());
            return;
        }

        makeKSub(k - 1, path.append("0"), kSubList);
        path.deleteCharAt(path.length() - 1);

        makeKSub(k - 1, path.append("1"), kSubList);
        path.deleteCharAt(path.length() - 1);
    }

    public static boolean hasAllCodes2(String s, int k) {
        if (s == null || k <= 0 || k > s.length()) {
            return false;
        }

        //计算s的所有子串，通过HashSet去重
        Set<String> kSubList = new HashSet<>();
        for (int i = 0; i + k <= s.length(); i++) {
            kSubList.add(s.substring(i, i + k));
        }

        //当子串个数相等时，满足条件
        return kSubList.size() == Math.pow(2, k);
    }
}
