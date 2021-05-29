package com.java.xknowledge.leetcode.tree.medium.isvalidbst;

/**
 * 98. 验证二叉搜索树
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree/
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

    public boolean isValidBST(TreeNode root) {
        //先递归到叶子节点，默认为true
        if (root == null) return true;

        //再判断左右子树是二叉树，并且左子树所有值都小于root，右子树值都大于root才是二叉搜索树
        return isValidBST(root.left) && isValidBST(root.right) && isMax(root, root.left)
                && isMin(root, root.right);
    }

    //递归判断root的值都小于right子树
    private boolean isMin(TreeNode root, TreeNode right) {
        if (right == null) return true;
        if (root.val >= right.val) return false;
        return isMin(root, right.left) && isMin(root, right.right);
    }

    //递归判断root的值都大于left子树
    private boolean isMax(TreeNode root, TreeNode left) {
        if (left == null) return true;
        if (root.val <= left.val) return false;
        return isMax(root, left.left) && isMax(root, left.right);
    }
}
