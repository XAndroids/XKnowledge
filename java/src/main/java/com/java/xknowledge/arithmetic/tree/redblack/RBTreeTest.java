package com.java.xknowledge.arithmetic.tree.redblack;

/**
 * 红黑树测试类
 * 参考：《享学1：010 树(下)：红黑树旋转理论及其应用》
 */
class RBTreeTest {
    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        rbTree.insert(2);
        rbTree.insert(4);
        rbTree.insert(6);
        rbTree.insert(8);
        rbTree.insert(10);
        rbTree.insert(12);
        rbTree.print();
    }
}
