package com.java.xknowledge.leetcode.list.medium.removenthfromend;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode font = head;
        for (int i = 0; i < n; i++) {//使用font前指针先移动n位置
            font = font.next;
        }

        ListNode temp = new ListNode(0, head);//返回顺序链表，添加temp头辅助节点
        ListNode end = temp;//使用end后指针向后移动，保持n的差距，直到font移动到末尾，此时正好找到第n个节点
        while (font != null) {
            font = font.next;
            end = end.next;
        }

        end.next = end.next.next;//删除节点

        return temp.next;//极端如[1]，删除倒数第1个，返回[]，需要辅助temp节点
    }
}
