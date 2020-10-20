package com.java.xknowledge.arithmetic.linearlist.linkedlist;

/**
 * 单向链表
 * 参考：《享学1：002链表（下）：轻松写出正确的链表算法，并实现LRU缓存淘汰算法》
 */
public class LinkedList<T> {
    protected Node list;//头节点
    protected int size;//长度

    /**
     * 向头部添加数据
     *
     * @param data 要添加的数据
     */
    public void put(T data) {
        Node curNode = new Node(data, list);//常见新节点，下一个节点是当前链表头节点
        list = curNode;//将链表头索引指向新插入的头节点
        size++;//链表长度+1
    }

    /**
     * 向指定位置添加数据
     *
     * @param index 指定位置索引
     * @param data  要添加的数据
     */
    public void put(int index, T data) {
        checkIndex(index);//检查插入位置索引是否合法
        Node cur = list;//插入位置的节点cur
        Node head = list;//插入位置节点的前一个节点head
        for (int i = 0; i < index; i++) {//遍历找到要插入的位置
            head = cur;
            cur = cur.next;
        }
        Node node = new Node(data, cur);//创建要插入的节点，下一个节点是原来该位置的节点
        head.next = node;//原来位置前一个节点的下一个节点指向插入的节点
        size++;//链表长度+1
    }

    /**
     * 从头部删除数据
     *
     * @return 删除的数据
     */
    public T remove() {
        if (list != null) {//如果链表不为空
            Node node = list;//临时保存链表头部节点
            list = list.next;//将头引用指向下一个节点
            node.next = null;//断开原头节点的下一个节点
            size--;//完成移除，链表长度-1
            return node.data;//返回移除节点的数据
        }
        return null;//如果链表为空，返回空
    }

    /**
     * 从指定位置删除数据
     *
     * @param index 指定位置索引
     * @return 删除的数据
     */
    public T remove(int index) {
        checkIndex(index);//检查移除位置索引的合法性
        Node cur = list;//移除位置的节点cur
        Node head = list;//移除位置的前一个节点head
        for (int i = 0; i < index; i++) {//遍历找到要移除的节点
            head = cur;
            cur = cur.next;
        }
        head.next = cur.next;//将要移除节点的前一个节点的下一个节点指向要移除节点的下一个节点，"绕过"要移除的节点
        cur.next = null;//"断开"移除节点的下一个节点索引
        size--;//完成移除，链表长度-1
        return cur.data;//返回移除节点的数据
    }

    /**
     * 从尾部删除数据
     *
     * @return 删除的数据
     */
    public T removeLast() {
        if (list != null) {//如果链表不为空
            Node cur = list;//要移除的尾部节点
            Node head = list;//要移除尾部节点的前一个节点
            while (cur.next != null) {//循环遍历到摇移除的尾部节点
                head = cur;
                cur = cur.next;
            }
            head.next = null;//尾部节点的前一个节点"断开"和尾部节点的索引
            size--;//完成尾部节点的移除，链表长度-1
            return cur.data;//返回移除尾部节点的数据
        }
        return null;//如果链表为空，返回null
    }


    /**
     * 修改指定位置的数据
     *
     * @param index 指定位置索引
     * @param data  要修改的数据
     */
    public void set(int index, T data) {
        checkIndex(index);//检查移除位置索引的合法性
        Node node = list;//要修改的节点node
        for (int i = 0; i < index; i++) {//循环遍历，找到要修改指定索引的节点
            node = node.next;
        }
        node.data = data;//返回该节点的数据
    }

    /**
     * 获取头部节点数据
     *
     * @return 获取的数据
     */
    public T get() {
        if (list != null) {//如果链表不为空
            return list.data;//返回链表头部节点的数据
        }
        return null;//如果链表为空，则返回null
    }

    /**
     * 获取指定位置的节点数据
     *
     * @param index 指定位置索引
     * @return 获取的数据
     */
    public T get(int index) {
        checkIndex(index);//检查移除位置索引的合法性
        Node node = list;//要获取的节点node
        for (int i = 0; i < index; i++) {//循环遍历，找到要获取的指定索引的节点
            node = node.next;
        }
        return node.data;//返回指定节点的数据
    }

    //检查索引是会否合法
    protected void checkIndex(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException("index: " + index + ", size：" + size);
        }
    }

    @Override
    public String toString() {
        Node node = list;
        for (int i = 0; i < size; i++) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
        return super.toString();
    }


    /**
     * 链表节点
     */
    protected class Node {
        public T data;//节点数据
        public Node next;//下一个节点

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
