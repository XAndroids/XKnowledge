package com.java.xknowledge.leetcode.list.easy.deletenode;

/**
 * 剑指 Offer 18. 删除链表的节点
 * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 * 题解：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/solution/jian-zhi-offer-18-shan-chu-lian-biao-de-b6xb5/
 */
class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        //参数校验
        if (head == null) {
            return null;
        }

        //定义辅助节点temp/pre/cur
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode pre = temp;
        ListNode cur = head;

        //查找val相等的节点位置
        while (cur != null && cur.val != val) {
            cur = cur.next;
            pre = pre.next;
        }

        //在到达尾部之前，找到相等val的节点，则删除该节点
        if (cur != null) {
            pre.next = cur.next;
        }

        //返回辅助temp节点的下一个节点
        return temp.next;
    }
}
