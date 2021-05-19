package com.java.xknowledge.leetcode.string.medium.permutation;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 */
class Solution {
    public String[] permutation(String s) {
        //参数校验
        if (s == null || s.length() == 1) return new String[]{s};

        //aab此种case，可能出现重复的全排列故使用Set去重
        Set<String> resultSet = new HashSet<>();
        //全排列字符数组
        char[] characters = s.toCharArray();
        //是否在排列中使用标记数组
        boolean[] used = new boolean[s.length()];
        //全排列路径
        StringBuilder path = new StringBuilder();
        //深度递归全排列
        dfs(characters, 0, path, used, resultSet);

        //返回结果字符串集合
        return resultSet.toArray(new String[0]);
    }

    /**
     * 深度递归全排列
     *
     * @param characters 排列字符数组
     * @param depth      排列深度
     * @param path       排列路径
     * @param used       排列使用标记数组
     * @param resultSet  全排列结果集合
     */
    private void dfs(char[] characters, int depth, StringBuilder path, boolean[] used, Set<String>
            resultSet) {
        //如果排列深度打包了，则添加结果集合
        if (depth == characters.length) {
            resultSet.add(path.toString());
            return;
        }

        //遍历当前使用的标记数组
        for (int i = 0; i < used.length; i++) {
            if (used[i]) {//如果已经参与排列使用则进行下一轮循环
                continue;
            }
            //否则使用字符拼接
            path.append(characters[i]);
            used[i] = true;
            //递归下一层排列
            dfs(characters, depth + 1, path, used, resultSet);
            //回溯本层次排列
            path.deleteCharAt(path.length() - 1);
            used[i] = false;
        }
    }
}
