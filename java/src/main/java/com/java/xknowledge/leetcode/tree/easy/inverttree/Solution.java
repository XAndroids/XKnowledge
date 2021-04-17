package com.java.xknowledge.leetcode.tree.easy.inverttree;

/**
 * 226. 翻转二叉树
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree/
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

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {//如果递归到叶子节点的左null右null节点，返回null
            return null;
        }

        TreeNode left = invertTree(root.left);//左子树翻转后赋值为右子树
        TreeNode right = invertTree(root.right);//右子树翻转后赋值给左子树
        root.left = right;
        root.right = left;

        return root;//返回完成翻转的子树
    }

    public static void main(String[] args) {

    }
}
