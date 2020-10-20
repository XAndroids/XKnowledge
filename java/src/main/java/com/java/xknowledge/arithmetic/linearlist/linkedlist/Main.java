package com.java.xknowledge.arithmetic.linearlist.linkedlist;

class Main {
    public static void main(String[] args) {
        //单向链表测试
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.put(i);
        }
        list.toString();
        list.put(3, 3);
        list.toString();
        list.put(8);
        list.toString();
        System.out.println(list.get(2));

        //Lru缓存单向链表测试
        LruLinkedList<Integer> lruLinkedList = new LruLinkedList<>(5);
        for (int i = 0; i < 4; i++) {
            lruLinkedList.lruPut(i);
        }
        lruLinkedList.toString();
        System.out.println(lruLinkedList.lruGet(3));
        lruLinkedList.toString();
        lruLinkedList.lruPut(20);
        lruLinkedList.toString();
        lruLinkedList.lruPut(18);
        lruLinkedList.toString();
    }
}
