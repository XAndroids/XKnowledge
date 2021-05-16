package com.java.xknowledge.leetcode.array.medium.combinationsum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii/
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<>();

        //参数校验
        if (candidates == null) return resultList;

        //避免重复1：排序！
        Arrays.sort(candidates);

        //递归+回溯查找结果
        Deque<Integer> path = new LinkedList<>();
        dfs(candidates, 0, path, target, resultList);

        return resultList;
    }

    private void dfs(int[] candidates, int begin, Deque<Integer> path, int target,
                     List<List<Integer>> resultList) {
        if (target < 0) return;
        if (target == 0) {
            resultList.add(new ArrayList<>(path));
        }

        for (int i = begin; i < candidates.length; i++) {
            //避免重复2：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用continue！
            //避免：[[1,2,5],[1,7],[1,6,1],[2,6],[2,1,5],[7,1]]，中[1,7]，[7,1]等重复节点
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[begin]);
            dfs(candidates, i + 1, path, target - candidates[i], resultList);
            path.removeLast();
        }
    }
}
