package com.java.xknowledge.leetcode.tree.easy.isbalanced;

/**
 * 题目：110. 平衡二叉树
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree/
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

    public boolean isBalanced(TreeNode root) {
        //参数校验
        if (root == null) return true;

        //两层递归写法，一层递归计算深度，一层递归判断平衡二叉树
        //左右子树是平衡二叉树，并且左右子树的高度差<=1
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(depth(root.left)
                - depth(root.right)) <= 1;
    }

    public int depth(TreeNode root) {
        //递归退出条件，递归遍历到叶子节点，默认返回true
        if (root == null) {
            return 0;
        }

        //分别计算左右子树的高度
        int leftHeight = depth(root.left);
        int rightHeight = depth(root.right);

        //返回当前子树的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
