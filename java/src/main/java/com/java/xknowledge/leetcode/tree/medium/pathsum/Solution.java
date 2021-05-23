package com.java.xknowledge.leetcode.tree.medium.pathsum;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public List<List<Integer>> pathSum(TreeNode root, int target) {
        //参数校验
        if (root == null) return null;

        //递归寻找N数之和
        List<List<Integer>> resultList = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        pathSumHelper(root, path, target, resultList);

        return resultList;
    }

    private void pathSumHelper(TreeNode root, Deque<Integer> patch, int target, List<List<Integer>>
            resultList) {
        if (root == null) {
            return;
        }

        patch.addLast(root.val);
        target = target - root.val;

        //必须是叶子节点，所有root.lef和root.right == null
        if (target == 0 && root.left == null && root.right == null) {
            resultList.add(new ArrayList<>(patch));
        }

        pathSumHelper(root.left, patch, target, resultList);
        pathSumHelper(root.right, patch, target, resultList);

        //回溯
        patch.removeLast();
    }
}
