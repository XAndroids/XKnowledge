package com.java.xknowledge.arithmetic.tree.bst;

/**
 * BS树：二叉排序树
 * 参考：《享学1：009 树(中)：二叉排序树及二叉平衡树原理及手写实现》
 */
class BSTreeTest {
    public static void main(String[] args) {
        //注意：节点不同的插入顺序，会构造出不同的二叉排序树结构，高度无法保证，查找时间无法保证，故会有二叉平衡树
        BSTree.Node root = BSTree.insert(null, 20);
        root = BSTree.insert(root, 30);
        root = BSTree.insert(root, 40);
        root = BSTree.insert(root, 10);
        root = BSTree.insert(root, 25);

        BSTree.printTree(root);
    }
}
