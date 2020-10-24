package com.java.xknowledge.arithmetic.tree.avl;

/**
 * AVL树：二叉平衡树
 * 参考：《享学1：009 树(中)：二叉排序树及二叉平衡树原理及手写实现》
 */
class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree.Node root = AVLTree.insert(null, 30);
        root = AVLTree.insert(root, 20);
        root = AVLTree.insert(root, 40);
        root = AVLTree.insert(root, 10);
        root = AVLTree.insert(root, 25);

        //插入节点在失衡结点的左子树的左边，执行LL旋转
        root = AVLTree.insert(root, 5);
        //打印树，按照先打印左子树，再打印右子树的方式
        AVLTree.printTree(root);

        root = AVLTree.insert(root, 21);
        //插入节点在失衡结点的右子树的左边，执行RL旋转，执行失衡节点右子树LL，失衡节点RR旋转
        root = AVLTree.insert(root, 23);
        AVLTree.printTree(root);
    }
}
