package com.java.xknowledge.leetcode.other.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * 题目：https://leetcode-cn.com/problems/lru-cache/submissions/
 * 题解：https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 */
class LRUCache2 {
    //定义双向链节点
    class ListNode {
        int key;//节点key
        int value;//节点value
        ListNode pre;//双连表前节点
        ListNode next;//双链表后节点

        public ListNode() {
        }

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, ListNode> cacheMap;//HashMap保存缓存数据
    ListNode pre;//双链表保存获取顺序
    ListNode tail;
    int capacity;

    public LRUCache2(int capacity) {
        cacheMap = new HashMap<>();//初始化HashMap

        pre = new ListNode();//初始化双链表
        tail = new ListNode();
        pre.next = tail;
        tail.pre = pre;

        this.capacity = capacity;//初始化容量
    }

    public int get(int key) {
        ListNode listNode = cacheMap.get(key);
        if (listNode == null) {
            return -1;//如果没有相关缓存则返回-1
        }
        removeToHead(listNode);//如果有则移动到链表头
        return listNode.value;//返回节点值
    }

    public void put(int key, int value) {
        ListNode listNode = cacheMap.get(key);
        if (listNode == null) {
            //如果没有相关节点，则构造新节点
            listNode = new ListNode(key, value);
            //添加到双链表头，缓存Map
            addToHead(listNode);
            cacheMap.put(key, listNode);

            //如果缓存大小超过限制，则移除链表尾部节点
            if (cacheMap.size() > capacity) {
                cacheMap.remove(tail.pre.key);
                removeNode(tail.pre);
            }
        } else {
            //如果有缓存相关节点，则更新数据，移动到链表头
            listNode.value = value;
            removeToHead(listNode);
        }
    }

    /**
     * 向双链表头部添加节点
     */
    private void addToHead(ListNode listNode) {
        listNode.next = pre.next;
        listNode.pre = pre;
        pre.next.pre = listNode;
        pre.next = listNode;
    }

    /**
     * 移除双链表最后的节点
     */
    private void removeNode(ListNode listNode) {
        listNode.pre.next = listNode.next;
        listNode.next.pre = listNode.pre;
    }

    /**
     * 将节点移动到双链表头部
     */
    private void removeToHead(ListNode listNode) {
        removeNode(listNode);
        addToHead(listNode);
    }
}
