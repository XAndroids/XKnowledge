package com.java.xknowledge.arithmetic.tree.redblack;

/**
 * 红黑树
 */
public class RBTree<T extends Comparable<T>> {
    private RBTNode<T> mRoot;    // 根结点

    private static final boolean RED = false;//红色是false
    private static final boolean BLACK = true;//黑色是true

    public RBTree() {
        mRoot = null;
    }

    /**
     * 获取红黑树节点的父节点
     *
     * @param node 获取目标节点
     * @return 返回其父节点
     */
    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node != null ? node.parent : null;
    }

    /**
     * 获取红黑树节点的颜色
     *
     * @param node 获取目标节点
     * @return 返回节点的颜色
     */
    private boolean colorOf(RBTNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    /**
     * 节点是否是红色
     *
     * @param node 判断节点
     * @return 是否是红色
     */
    private boolean isRed(RBTNode<T> node) {
        return (node != null) && (node.color == RED);
    }

    /**
     * 节点是否是黑色
     *
     * @param node 判断节点
     * @return 是否是黑色
     */
    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }

    /**
     * 将节点设置为黑色
     *
     * @param node 设置的节点
     */
    private void setBlack(RBTNode<T> node) {
        if (node != null)
            node.color = BLACK;
    }

    /**
     * 将节点设置为红色
     *
     * @param node 设置的节点
     */
    private void setRed(RBTNode<T> node) {
        if (node != null)
            node.color = RED;
    }

    /**
     * 设置节点的父节点
     *
     * @param node   孩子节点
     * @param parent 父节点
     */
    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node != null)
            node.parent = parent;
    }

    /**
     * 设置节点的颜色
     *
     * @param node  设置的节点
     * @param color 设置的颜色
     */
    private void setColor(RBTNode<T> node, boolean color) {
        if (node != null)
            node.color = color;
    }


    /**
     * 新建节点key，将其插入到红黑树中
     *
     * @param key 插入节点的键值
     */
    public void insert(T key) {
        RBTNode<T> node = new RBTNode<T>(key, BLACK, null, null, null);
        insert(node);
    }

    /**
     * 将节点插入到红黑树中
     *
     * @param node 要插入的节点
     */
    private void insert(RBTNode<T> node) {
        int compare;
        RBTNode<T> insertRootNode = null;//插入节点的父节点
        RBTNode<T> curRootNode = this.mRoot;//插入节点

        //将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (curRootNode != null) {//从根节点开始遍历，查找node节点要插入节点的根节点insertRootNode
            insertRootNode = curRootNode;
            compare = node.key.compareTo(curRootNode.key);
            if (compare < 0) {
                curRootNode = curRootNode.left;
            } else {
                curRootNode = curRootNode.right;
            }
        }

        //将插入节点node，插入到树中查找到的插入节点insertRootNode
        node.parent = insertRootNode;
        if (insertRootNode != null) {
            //树不为空，根据和插入节点insertRootNode的比较分别插入左右子节点
            compare = node.key.compareTo(insertRootNode.key);
            if (compare < 0)
                insertRootNode.left = node;
            else
                insertRootNode.right = node;
        } else {
            //树为空，则直接为根节点
            this.mRoot = node;
        }

        //设置插入节点node的颜色为红色
        node.color = RED;

        //插入后，可能不再满足红黑树则重新调整为红黑树
        insertFixUp(node);
    }

    /**
     * 红黑树插入修正函数
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；目的是将它重新塑造成一颗红黑树。
     *
     * @param node 插入的结点
     */
    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, gparent;

        //所有平衡条件的第一个就是：如果父节点存在，并且父节点的颜色是红色
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);//祖父节点
            //若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                RBTNode<T> uncle = gparent.right;
                //情况2：新节点的父节点(前面while循环判断)和叔叔节点是红色
                //先插入新节点（红色），（变色）新节点的父节点、叔父节点和祖父节点都需要变色
                if (isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // 情况5：如果新节点的父节点是红色(前面while循环已判断)，叔叔是黑色(红色continue退出)，同时新节点是其父节点的右子节点，同时父亲是祖父节点的左子节点（前面已判断）
                // 两次旋转，先左后右
                if (parent.right == node) {
                    RBTNode<T> tmp;
                    leftRotate(parent);//以父节点为根节点的子树，进行左旋转
                    tmp = parent;
                    parent = node;//插入完毕后插入节点成了新的根节点/父节点
                    node = tmp;
                }

                // 情况3：如果新节点的父节点是红色（前面while循环已判断）同时叔叔是黑色(红色continue退出)，新节点是父亲节点的左孩子，且父节点是其父节点的左孩子(前面whilde循环已判断)
                //进行一次(以祖父节点为轴)右旋转调换新节点和父节点角色
                setBlack(parent);//FIXME变色是？？
                setRed(gparent);
                rightRotate(gparent);//右旋转
            } else {
                //若“父节点”是“祖父节点的右孩子”
                RBTNode<T> uncle = gparent.left;
                if (isRed(uncle)) {  // 情况2：叔叔节点是红色
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // 情况6：叔叔是黑色，且当前节点是左孩子(两次旋转，先右后左)
                if (parent.left == node) {
                    RBTNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // 情况4：叔叔是黑色，且当前节点是右孩子。（一次左旋转）
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        // 将根节点设为黑色
        setBlack(this.mRoot);
    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      13                               17
     *     /  \                             /  \
     *   nul  17                          13    27
     *       / \      --(左旋)-.          / \    / \
     *     nul 27                      nul nul nul nul
     *         / \
     *      nul  nul
     *
     *
     */
    private void leftRotate(RBTNode<T> rootNode) {
        // 设置原来的根节点rootNode的右孩子为curRoot-新的根节点
        RBTNode<T> curRoot = rootNode.right;

        // 将 “新根节点curRoot的左孩子null” 设为 “原根节点rootNode的右孩子”；
        // 如果curRoot的左孩子非空，将 “rootNode” 设为 “curRoot的左孩子的父亲”
        rootNode.right = curRoot.left;
        if (curRoot.left != null) {
            curRoot.left.parent = rootNode;
        }

        // 将 “原来根节点rootNode的父亲” 设为 “新根节点curRoot的父亲”
        curRoot.parent = rootNode.parent;
        // 如果 “原来根节点rootNode的父亲” 是空节点，即是根节点，则将“新根节点curRoot设为根节点
        if (rootNode.parent == null) {
            this.mRoot = curRoot;
        } else {
            // 如果原来根节点rootNode是它父节点的左孩子，则将新根节点curRoot设为“原来根节点rootNode的父节点的左孩子”
            if (rootNode.parent.left == rootNode) {
                rootNode.parent.left = curRoot;
            } else {
                // 如果原来根节点rootNode是它父节点的右孩子，则将新根节点curRoot设为“原来根节点rootNode的父节点的右孩子”
                rootNode.parent.right = curRoot;
            }
        }

        // 将 “原来根节点rootNode” 设为 “新根节点curRoot的左孩子”
        curRoot.left = rootNode;
        // 将 “原来根节点rootNode的父节点” 设为 “新根节点curRoot”
        rootNode.parent = curRoot;
    }

    /*
     * 对红黑树的节点(8)进行右旋转
     *
     * 右旋示意图(对节点8进行右旋)：
     *            13                                 8
     *           /  \                             /     \
     *          8   nul                          1      13
     *         /  \      --(右旋)-              / \      / \
     *        1   nul                        nul nul nul  nul
     *       / \
     *     nul nul
     *
     */
    private void rightRotate(RBTNode<T> rootNode) {
        // 设置原来的根节点rootNode的左孩子为curRoot-新的根节点
        RBTNode<T> curRoot = rootNode.left;

        // 将 “新根节点curRoot的右孩子null” 设为 “原根节点rootNode的左孩子”；
        // 如果curRoot的右孩子非空，将 “rootNode” 设为 “curRoot的右孩子的父亲”
        rootNode.left = curRoot.right;
        if (curRoot.right != null)
            curRoot.right.parent = rootNode;

        // 将 “原来根节点rootNode的父亲” 设为 “新根节点curRoot的父亲”
        curRoot.parent = rootNode.parent;
        // 如果 “原来根节点rootNode的父亲” 是空节点，即是根节点，则将“新根节点curRoot设为根节点
        if (rootNode.parent == null) {
            this.mRoot = curRoot;
        } else {
            // 如果原来根节点rootNode是它父节点的右孩子，则将新根节点curRoot设为“原来根节点rootNode的父节点的右孩子”
            if (rootNode == rootNode.parent.right) {
                rootNode.parent.right = curRoot;
            } else {
                // 如果原来根节点rootNode是它父节点的左孩子，则将新根节点curRoot设为“原来根节点rootNode的父节点的左孩子”
                rootNode.parent.left = curRoot;
            }
        }

        // 将 “原来根节点rootNode” 设为 “新根节点curRoot的右孩子”
        curRoot.right = rootNode;
        // 将 “原来根节点rootNode的父节点” 设为 “新根节点curRoot”
        rootNode.parent = curRoot;
    }

    /**
     * 从红黑树删除节点(key),并返回被删除的节点
     *
     * @param key 删除节点的key
     */
    public void remove(T key) {
        RBTNode<T> node;

        if ((node = search(mRoot, key)) != null) {
            remove(node);
        }
    }

    /**
     * 删除结点(node)，并返回被删除的结点
     *
     * @param deleteNode
     */
    private void remove(RBTNode<T> deleteNode) {
        RBTNode<T> child, parent;
        boolean color;

        // 如果被删除的节点deleteNode的"左右孩子都不为空"的情况
        if ((deleteNode.left != null) && (deleteNode.right != null)) {
            // 被删节点的后继节点。(称为"取代节点")，用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBTNode<T> replace = deleteNode;

            // 获取后继节点
            replace = replace.right;
            while (replace.left != null) {
                replace = replace.left;
            }

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (parentOf(deleteNode) != null) {
                if (parentOf(deleteNode).left == deleteNode) {
                    //如果删除的节点是父节点的左节点，则删除后父节点的左节点替换为新节点
                    parentOf(deleteNode).left = replace;
                } else {
                    //如果删除的节点是父节点的右节点，则删除后父节点的右节点替换为新节点
                    parentOf(deleteNode).right = replace;
                }
            } else {
                // "node节点"是根节点，更新根节点。
                this.mRoot = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = parentOf(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == deleteNode) {
                parent = replace;
            } else {
                // child不为空
                if (child != null) {
                    setParent(child, parent);
                }
                parent.left = child;

                replace.right = deleteNode.right;
                setParent(deleteNode.right, replace);
            }

            replace.parent = deleteNode.parent;
            replace.color = deleteNode.color;
            replace.left = deleteNode.left;
            deleteNode.left.parent = replace;

            if (color == BLACK) {
                removeFixUp(child, parent);
            }
            return;
        }

        if (deleteNode.left != null) {
            child = deleteNode.left;
        } else {
            child = deleteNode.right;
        }

        parent = deleteNode.parent;
        // 保存"取代节点"的颜色
        color = deleteNode.color;

        if (child != null) {
            child.parent = parent;
        }

        // "node节点"不是根节点
        if (parent != null) {
            if (parent.left == deleteNode) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            this.mRoot = child;
        }

        if (color == BLACK) {
            removeFixUp(child, parent);
        }
    }

    /*
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明：
     *     node 待修正的节点
     */
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;

        while ((node == null || isBlack(node)) && (node != this.mRoot)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right == null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.mRoot;
                    break;
                }
            } else {
                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left == null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }

        if (node != null)
            setBlack(node);
    }

    public RBTNode<T> search(T key) {
        return search(mRoot, key);
    }

    /**
     * (递归实现)查找"红黑树x"中键值为key的节点
     *
     * @param rootNode 查找红黑树的根节点
     * @param key      要查找节点的key
     * @return 返回查找到的节点
     */
    private RBTNode<T> search(RBTNode<T> rootNode, T key) {
        if (rootNode == null) {
            return null;
        }

        int cmp = key.compareTo(rootNode.key);
        if (cmp < 0) {//如果比当前根节点小，从左子树查找
            return search(rootNode.left, key);
        } else if (cmp > 0) {//如果比当前根节点大，从右子树查找
            return search(rootNode.right, key);
        } else {
            //否则，相等返回该节点
            return rootNode;
        }
    }

    public RBTNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    /*
     * (非递归实现)查找"红黑树x"中键值为key的节点
     */
    private RBTNode<T> iterativeSearch(RBTNode<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);

            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x;
        }

        return x;
    }

    public T minimum() {
        RBTNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * 查找最小结点：返回tree为根结点的红黑树的最小结点。
     */
    private RBTNode<T> minimum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T maximum() {
        RBTNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * 查找最大结点：返回tree为根结点的红黑树的最大结点。
     */
    private RBTNode<T> maximum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }

    /*
     * 找结点(x)的后继结点。即，查找"红黑树中数据值大于该结点"的"最小结点"。
     */
    public RBTNode<T> successor(RBTNode<T> x) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (x.right != null)
            return minimum(x.right);

        // 如果x没有右孩子。则x有以下两种可能：
        // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
        // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        RBTNode<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /*
     * 找结点(x)的前驱结点。即，查找"红黑树中数据值小于该结点"的"最大结点"。
     */
    public RBTNode<T> predecessor(RBTNode<T> x) {
        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (x.left != null)
            return maximum(x.left);

        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        RBTNode<T> y = x.parent;
        while ((y != null) && (x == y.left)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 前序遍历"红黑树"
     */
    private void preOrder(RBTNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * 中序遍历"红黑树"
     */
    private void inOrder(RBTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * 后序遍历"红黑树"
     */
    private void postOrder(RBTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    /*
     * 销毁红黑树
     */
    private void destroy(RBTNode<T> tree) {
        if (tree == null)
            return;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree = null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }


    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }

    /*
     * 打印"红黑树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(RBTNode<T> tree, T key, int direction) {

        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree) ? "R" : "B", key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    /**
     * 红黑树节点
     */
    public class RBTNode<T extends Comparable<T>> {
        T key;// 关键字(键值)
        boolean color;// 颜色
        RBTNode<T> left;// 左孩子
        RBTNode<T> right;// 右孩子
        RBTNode<T> parent;// 父结点

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        public String toString() {
            return "" + key + (this.color == RED ? "(R)" : "B");
        }
    }

}