package com.java.xknowledge.arithmetic.tree.bst;

/**
 * BST（Binary Sort Tree）
 */
class BSTree {

    /**
     * 向BST树插入节点
     *
     * @param node 待插入的节点
     * @return BST树根节点
     */
    static Node insert(Node root, int data) {
        if (root == null) {//如果BST树为Null，则直接为根节点
            root = new Node(data);
            return root;
        }

        if (data <= root.data) {//如果小于根节点，则插入左子树上
            root.leftChild = insert(root.leftChild, data);//递归，以左子节点为根节点继续执行插入
            //注意：BST和AVL的区别，就是插入后没有旋转调整，保证高度！！！
        } else {//如果大于根节点，则插入右子树上
            root.rightChild = insert(root.rightChild, data);//递归，以右子节点为根节点继续执行插入
            //注意：BST和AVL的区别，就是插入后没有旋转调整，保证高度！！！
        }

        return root;
    }

    public static void printTree(Node root) {
        System.out.println(root.toString());
        if (root.leftChild != null) {
            System.out.print("left:");
            printTree(root.leftChild);
        }
        if (root.rightChild != null) {
            System.out.print("right:");
            printTree(root.rightChild);
        }
    }

    /**
     * 二叉排序树节点
     *
     * @param <T>
     */
    static class Node<T> {
        private int data;//数据
        private Node leftChild;//左子节点
        private Node rightChild;//右子节点

        public Node(int data) {
            this.data = data;
        }

        public String toString() {
            return "Node[data=" + data + "]";
        }
    }
}
