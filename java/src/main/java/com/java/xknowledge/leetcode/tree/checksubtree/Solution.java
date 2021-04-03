package com.java.xknowledge.leetcode.tree.checksubtree;

/**
 * 面试题 04.10. 检查子树
 * 链接：https://leetcode-cn.com/problems/check-subtree-lcci
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
    }

    /**
     * 比较两个子树是否是父子树
     */
    public static boolean checkSubTree(TreeNode tree, TreeNode subTree) {
        if (tree == null) {//遍历到最终叶子节点，也没有和subTree匹配的头节点，故return false
            return false;
        }
        if (tree.val == subTree.val) {//如果两个节点值相同，则开始匹配从该节点开始子树是否相等
            if (matchSubTree(tree, subTree)) {
                return true;
            }
        }

        //如果不相等，则继续从左子树和右子树继续检查是否是子树
        return checkSubTree(tree.left, subTree) || checkSubTree(tree.right, subTree);
    }

    /**
     * 比较两个子树是否相等
     */
    private static boolean matchSubTree(TreeNode tree, TreeNode subTree) {
        if (tree == null && subTree == null) {//如果比较到最后叶子节点，每个节点都相等则返回true
            return true;
        }
        //如果任意一个节点为null，另个非null，或者两个节点的值不同，则返回false终止递归
        if (tree == null || subTree == null || tree.val != subTree.val) {
            return false;
        }

        //当前节点的值相同，则继续递归左右子节点都相同
        return matchSubTree(tree.left, subTree.left) && matchSubTree(tree.right, subTree.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(3);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(3);

        System.out.println(checkSubTree(root1, root2));
    }
}
