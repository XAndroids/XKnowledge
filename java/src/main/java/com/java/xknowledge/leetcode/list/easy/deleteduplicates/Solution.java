package com.java.xknowledge.leetcode.list.easy.deleteduplicates;

/**
 * 83. 删除排序链表中的重复元素
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
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

    //自我解法：快慢指针，同数组删除重复元素
    public ListNode deleteDuplicates(ListNode head) {
        //参数校验
        if (head == null) return null;
        //快慢指针
        ListNode slow = head;//当前不重复元素
        ListNode fast = head.next;

        //while循环fast指针未到结尾，判断非重复元素交换值
        while (fast != null) {
            if (fast.val != slow.val) {
                slow = slow.next;
                slow.val = fast.val;
            }
            fast = fast.next;
        }
        //slow = null，切断重复元素节点
        slow.next = null;

        //返回head
        return head;
    }

    //官方解法：遍历节点，发现相邻两个节点相同则删除下一个节点
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;

        ListNode cur = head;
        //遍历链表，有序下面要比较cur.next.val，故避免空指针使用cur.next判断链表尾部
        while (cur.next != null) {
            //如果连续两个节点值相同，则删除一个节点
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
