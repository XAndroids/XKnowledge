package com.java.xknowledge.arithmetic.linearlist.linkedlist;

/**
 * Lru缓存单向链表实现
 * 参考：《享学1：002链表（下）：轻松写出正确的链表算法，并实现LRU缓存淘汰算法》
 */
class LruLinkedList<T> extends LinkedList<T> {
    int memory_size;//缓存大小
    static final int DEFAULT_CAP = 5;//缓存默认大小

    public LruLinkedList() {
        this(DEFAULT_CAP);
    }

    public LruLinkedList(int memory_size) {
        this.memory_size = memory_size;
    }

    /**
     * 向lru缓存添加数据
     *
     * @param data 要缓存的数据
     */
    public void lruPut(T data) {
        if (size >= memory_size) {//如果超过最大缓存
            removeLast();//从链表尾部移除数据
        }
        put(data);//从链表头部添加数据
    }

    /**
     * 从lru缓存移除数据
     */
    public T lruRemove() {
        return removeLast();//从链表尾部移除数据
    }

    /**
     * 从lru缓存获取数据
     *
     * @param index 获取指定数据的索引
     * @return 指定索引的数据
     */
    public T lruGet(int index) {
        checkIndex(index);//检查获取数据索引的合法性
        Node node = list;//获取指定索引的节点
        Node pre = list;///获取指定索引的节点的前一个节点
        for (int i = 0; i < index; i++) {//循环遍历，查找到指定索引的节点
            pre = node;
            node = node.next;
        }
        T data = node.data;//获取指定节点的数据，用于后续返回
        pre.next = node.next;//删除获取的指定节点
        node.next = list;//将获取的节点添加到链表头部
        list = node;
        return data;//返回获取节点的数据
    }
}
