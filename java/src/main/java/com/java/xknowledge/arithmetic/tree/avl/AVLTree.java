package com.java.xknowledge.arithmetic.tree.avl;

/**
 * AVL树(Balance Binary Search Tred)
 */
class AVLTree {

    /**
     * 向AVL树插入节点
     *
     * @param node 待插入的节点
     * @return AVL树根节点
     */
    static Node insert(Node root, int data) {
        if (root == null) {//如果AVL树为Null，则直接为根节点
            root = new Node(data);
            return root;
        }

        if (data <= root.data) {//如果小于根节点，则插入左子树上
            root.leftChild = insert(root.leftChild, data);//递归，以左子节点为根节点继续执行插入
            //如果平衡被打破，插入左子树，左子树增加，故判断是不是左子树比右子树高度差>1，造成失衡
            if (getHeight(root.leftChild) - getHeight(root.rightChild) > 1) {
                if (data <= root.leftChild.data) {//插入左子树的左边
                    System.out.println("LL旋转");
                    root = LLRotate(root);//LL旋转调整
                } else {//插入左子树的右边
                    System.out.println("LR旋转");
                    root = LRRotate(root);//LR旋转
                }
            }
        } else {//如果大于根节点，则插入右子树上
            root.rightChild = insert(root.rightChild, data);//递归，以右子节点为根节点继续执行插入
            //如果平衡被打破，插入右子树，右子树增加，故判断是不是右子树比左子树高度差>1，造成失衡
            if (getHeight(root.rightChild) - getHeight(root.leftChild) > 1) {
                if (data <= root.rightChild.data) {//插入右子树的左边
                    System.out.println("RL旋转");//RL旋转
                    root = RLRotate(root);
                } else {
                    System.out.println("RR旋转");//RR旋转
                    root = RRRotate(root);
                }
            }
        }

        //重新调整root节点的高度值
        root.height = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
        return root;
    }

    /**
     * 执行LL旋转平衡调整
     * 左旋示意图(对结点20进行左旋)
     *      30                       20
     *     /  \                     /  \
     *    20  40                  10   30
     *   /  \      --LL旋转-       /   /  \
     *  10   25                  5   25   40
     *  /
     * 5
     *
     * @param root LL旋转调整的子树
     * @return 调整好的子树根节点
     */
    private static Node LLRotate(Node root) {//节点30失衡
        //LL平衡调整
        Node lastRoot = root.leftChild;//失衡节点30的左节点20为下一个根节点
        root.leftChild = lastRoot.rightChild;//新根节点20的右节点，成为失衡根节点30的左节点
        lastRoot.rightChild = root;//失衡根节点30，成为新根节点20的右节点

        //重新计算失衡节点30和新根节点20的高度
        root.height = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
        lastRoot.height = Math.max(getHeight(lastRoot.leftChild), getHeight(lastRoot.rightChild)) + 1;

        return lastRoot;
    }

    /**
     * 执行RR旋转平衡调整
     * 右旋示意图(对结点30进行左旋)
     *      20                          30
     *     /  \                        /  \
     *    10  30                     20   40
     *       /  \      --RR旋转-     /  \   \
     *      25  40                 10  25  50
     *           \
     *           50
     *
     * @param root RR旋转调整的子树
     * @return 调整好的子树根节点
     */
    private static Node RRRotate(Node root) {//节点20失衡
        //RR平衡调整
        Node lastRoot = root.rightChild;//失衡节点20的右节点30为下一个根节点
        root.rightChild = lastRoot.leftChild;//新根节点30的左节点，成为失衡根节点20的右节点
        lastRoot.leftChild = root;//失衡根节点20，成为新根节点的30的左节点

        //重新计算失衡节点20和新根节点30的高度
        root.height = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
        lastRoot.height = Math.max(getHeight(lastRoot.leftChild), getHeight(lastRoot.rightChild)) + 1;

        return lastRoot;
    }

    /**
     * 执行RL旋转平衡调整
     *
     * @param root RL旋转调整的子树
     * @return 调整好的子树根节点
     */
    private static Node RLRotate(Node root) {
        root.rightChild = LLRotate(root.rightChild);//先将失衡节点root的右子树进行LL平衡旋转
        return RRRotate(root);//再将失衡节点root进行RR旋转
    }

    /**
     * 执行LR旋转平衡调整
     *
     * @param root LR旋转调整的子树
     * @return 调整好的子树根节点
     */
    private static Node LRRotate(Node root) {
        root.leftChild = RRRotate(root.leftChild);//先将失衡的节点root的左子树进行RR平衡旋转
        return LLRotate(root);//再将失衡节点root进行LL平衡旋转
    }

    /**
     * 获取节点的高度
     *
     * @param node 获取的节点
     * @return 节点的高度
     */
    private static int getHeight(Node node) {
        return node == null ? -1 : node.height;//空树高度为-1
    }

    public static void printTree(Node root) {
        System.out.println(root.data);
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
     * @param <T>
     */
    static class Node<T> {
        private int data;//数据
        private Node leftChild;//左子节点
        private Node rightChild;//右子节点
        private int height;//高度

        public Node(int data) {
            this.data = data;
        }
    }


}
