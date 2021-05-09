package com.java.xknowledge.leetcode.tree.easy.sortedarraytobst;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
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

    public TreeNode sortedArrayToBST(int[] nums) {
        //参数校验
        if (nums == null || nums.length == 0) {
            return null;
        }

        //递归构造二叉树
        return sortedArrayToBST2(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST2(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        //取中间节点为根节点
        int mid = (start + end) / 2;
        TreeNode rootNode = new TreeNode(nums[mid]);

        //取小于根节点数组构造左子树
        rootNode.left = sortedArrayToBST2(nums, start, mid - 1);
        //取大于根节点数组构造右子树
        rootNode.right = sortedArrayToBST2(nums, mid + 1, end);

        return rootNode;
    }
}
