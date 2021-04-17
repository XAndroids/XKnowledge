package com.java.xknowledge.leetcode.tree.medium.buildtree;

import java.util.Arrays;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 题目：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 题解：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/dong-hua-yan-shi-105-cong-qian-xu-yu-zhong-xu-bian/
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //校验参数合法性、递归退出
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }

        //从前序遍历找到根节点，构造根节点
        int rootVal = preorder[0];
        TreeNode rootNode = new TreeNode(rootVal);


        //通过根节点从中序遍历中找到左子树和右子树
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                //找到了根节点，在创建相关的子树遍历集合
                int[] leftInOrder = Arrays.copyOfRange(inorder, 0, i);
                int[] rightInOrder = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                int[] leftPreOrder = Arrays.copyOfRange(preorder, 1, i + 1);
                int[] rightPreOrder = Arrays.copyOfRange(preorder, i + 1, preorder.length);

                //递归构造左子树和右子树
                rootNode.left = buildTree(leftPreOrder, leftInOrder);
                rootNode.right = buildTree(rightPreOrder, rightInOrder);

                break;//找到根节点之后，就没有必要继续执行了
            }
        }

        return rootNode;
    }

}
