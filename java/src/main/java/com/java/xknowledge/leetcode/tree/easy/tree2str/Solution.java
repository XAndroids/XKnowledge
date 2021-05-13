package com.java.xknowledge.leetcode.tree.easy.tree2str;

/**
 * 606. 根据二叉树创建字符串
 * 链接：https://leetcode-cn.com/problems/construct-string-from-binary-tree/
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

    //自己的写法
    public String tree2str(TreeNode root) {
        StringBuffer result = new StringBuffer();
        tree2strHelper(root, result);
        return result.toString();
    }

    private void tree2strHelper(TreeNode root, StringBuffer stringBuffer) {
        if (root == null) return;

        stringBuffer.append(root.val);

        if (root.left == null && root.right == null) return;

        stringBuffer.append("(");
        tree2strHelper(root.left, stringBuffer);
        stringBuffer.append(")");

        if (root.right != null) {
            stringBuffer.append("(");
            tree2strHelper(root.right, stringBuffer);
            stringBuffer.append(")");
        }
    }

    //官方的写法
    public String tree2str2(TreeNode root) {
        //如果root为null的返回""
        if (root == null) return "";
        //如果left和right都为null，则返回root.val
        if (root.left == null && root.right == null) return root.val + "";
        //如果right为null，则右子树一定有值，则返回root.val + root.left
        if (root.right == null) return root.val + "(" + tree2str2(root.left) + ")";
        //最后left和right都不为null，则拼接root.val + root.left + root.right
        return root.val + "(" + tree2str2(root.left) + ")" + "(" + tree2str2(root.right) + ")";
    }
}
