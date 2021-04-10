package com.java.xknowledge.leetcode.tree.mirrortree;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 */
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归法：递归节点交换左右节点
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {//递归遍历到叶子节点的子节点，退出递归
            return null;
        }

        TreeNode tempNode = root.right;//临时保存右子树
        root.right = mirrorTree(root.left);//递归完成左右子树的镜像，交换左右子树
        root.left = mirrorTree(tempNode);

        return root;//返回镜像好的子树root
    }
}
