package com.java.xknowledge.leetcode.tree.maxdepth;

/**
 * 104. 二叉树的最大深度
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 */
class Solution {
    public static class TreeNode {
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

    public static int maxDepth(TreeNode root) {
        return order(root);
    }

    /**
     * 递归遍历：
     * 从上往下开始计算，能算出每个叶子节点的深度，但是无法算出整个树的深度
     * 需要从下往上回调开始计算，树的深度= Max.(左子树深度,右子树深度)+1为当前节点的深度
     */
    public static int order(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        return Math.max(order(treeNode.left), order(treeNode.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(7);

        System.out.print(maxDepth(root));
    }
}
