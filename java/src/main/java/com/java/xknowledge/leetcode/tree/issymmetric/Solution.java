package com.java.xknowledge.leetcode.tree.issymmetric;

/**
 * 101. 对称二叉树
 * 链接：https://leetcode-cn.com/problems/symmetric-tree/
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

    public boolean isSymmetric(TreeNode root) {
        //递归思想：将同一层级的两个对称节点，互为比较判断
        return isSymmetricCheck(root.left, root.right);
    }

    public boolean isSymmetricCheck(TreeNode left, TreeNode right) {
        //左右子树都为null，则退出递归
        if (left == null && right == null) {
            return true;
        }

        //左右子树，明确不相等，则退出递归
        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        //否则继续递归左左和右右，或者左右和右左子树
        return isSymmetricCheck(left.left, right.right) && isSymmetricCheck(left.right, right.left);
    }
}
