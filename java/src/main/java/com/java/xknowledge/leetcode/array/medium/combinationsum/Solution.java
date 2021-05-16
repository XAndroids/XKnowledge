package com.java.xknowledge.leetcode.array.medium.combinationsum;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 * 链接：https://leetcode-cn.com/problems/combination-sum/
 */
class Solution {
    //深度遍历+回溯算法
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //深度+回溯算法
        List<List<Integer>> result = new ArrayList<>();

        //参数校验
        if (candidates == null || candidates.length == 0) return result;

        //DFS递归遍历的路径
        Deque<Integer> path = new LinkedList<>();
        dfs(candidates, 0, path, target, result);

        return result;
    }

    /**
     * @param candidates 组合的数组
     * @param begin      下次开始遍历索引
     * @param path       深度遍历的路径
     * @param target     每层遍历是否满足条件的目标
     * @param result     满足题目的结果集合
     */
    private void dfs(int[] candidates, int begin, Deque<Integer> path, int target, List<List
            <Integer>> result) {
        //如果目标为负数，说明该path路径无法满足条件，return返回
        if (target < 0) return;

        //如果target ==0，添加满足条件的路径
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            //由于元素可以无限重复被使用，故下一次递归还是从begin开始
            dfs(candidates, i, path, target - candidates[i], result);
            //递归结束，回溯candidates[i]进行其他路径的递归
            path.removeLast();
        }
    }
}
