package com.java.xknowledge.leetcode.tree.easy.mergetrees;

/**
 * 617. 合并二叉树
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
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

    /**
     * 递归实现：同时递归两个二叉树，递归退出条件：任意一个树节点为null（即少了一条腿）返回另一个节点的子树，跳出
     * 递归。否则mrege节点，继续递归；
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {//注意递归退出条件！任意一个数节点为空，后续就没必要递归了，把非空的子树拷贝即可！
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode mergeNode = new TreeNode();//否则两个都不为null，则Merge节点
        mergeNode.val = root1.val + root2.val;

        mergeNode.left = mergeTrees(root1.left, root2.left);//递归合并当前节点的左子节点和右子节点
        mergeNode.right = mergeTrees(root1.right, root2.right);

        return mergeNode;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(2);

        TreeNode resultRoot = mergeTrees(root1, root2);
        System.out.println(resultRoot.toString());
    }
}
