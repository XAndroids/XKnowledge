package com.java.xknowledge.leetcode.array.medium.permute;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 46. 全排列
 * 链接：https://leetcode-cn.com/problems/permutations/
 * 题解：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 */
class Solution {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();//全排列结果
        //回溯路径，必须使用队列！！列表使用num索引并不一定是结果位置
        Deque<Integer> path = new ArrayDeque<>(nums.length);
        boolean[] used = new boolean[nums.length];//回溯使用标记

        dfs(nums, 0, path, used, result);

        return result;
    }

    /**
     * 递归/回溯 全排列
     *
     * @param nums   待全排列的数组
     * @param depth  排列数组的位置
     * @param path   排列结果
     * @param used   排列使用标识
     * @param result 全排列结果
     */
    private static void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used,
                            List<List<Integer>> result) {
        if (depth == nums.length) {//当递归深度=nums.length时，说明全排列完毕，添加结果
            result.add(new ArrayList<>(path));
            return;
        }

        //遍历待排列nums和used使用数组，用未使用的数字进行后续排列
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);//如果nums数字没用使用则，添加全排列path
            used[i] = true;//更新used集合

            dfs(nums, depth + 1, path, used, result);//递归继续全排列

            used[i] = false;//全排列完毕，回溯回退使用标识和path集合
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
